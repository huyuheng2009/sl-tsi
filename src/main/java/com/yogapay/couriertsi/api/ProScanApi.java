package com.yogapay.couriertsi.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderProblem;
import com.yogapay.couriertsi.domain.OrderScan;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.OrderTransport;
import com.yogapay.couriertsi.domain.ProOrderReason;
import com.yogapay.couriertsi.domain.Substation;
import com.yogapay.couriertsi.domain.Transport;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigService;
import com.yogapay.couriertsi.services.OrderPackService;
import com.yogapay.couriertsi.services.OrderProblemService;
import com.yogapay.couriertsi.services.OrderScanService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.services.OrderTrackService;
import com.yogapay.couriertsi.services.OrderTransportService;
import com.yogapay.couriertsi.services.PackService;
import com.yogapay.couriertsi.services.ProOrderReasonService;
import com.yogapay.couriertsi.services.SubstationService;
import com.yogapay.couriertsi.services.TransportService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.DateUtils;
import com.yogapay.couriertsi.utils.RequestFile;


@Controller
@RequestMapping(value="/proscan")
@Scope("prototype")
public class ProScanApi extends BaseApi{
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private OrderService orderService  ;
	@Resource
	private OrderProblemService orderProblemService ;


	@Resource
	private SubstationService substationService ;
	@Resource
	private OrderTrackService orderTrackService ;
	@Resource private OrderScanService orderScanService ;
	@Resource
	private ProOrderReasonService proOrderReasonService ;


	@RequestMapping(value = "scan")	
	public void scan(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_no","reason_id",}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                BossUser bossUser = bossUserService.getByUserName(userName) ;
                Date nowDate = new Date() ;

                OrderInfo orderInfo = orderService.getByOrderNo(params.get("order_no")) ;
                if (orderInfo==null) {
			    	render(JSON_TYPE, CommonResponse.respFailJson("9999","运单号不存在", params.get("reqNo")), response);
					return ;
				}
                
                OrderProblem  orderProblem = orderProblemService.getByOrderNo(params.get("order_no")) ;
			    if (orderProblem!=null) {
			    	render(JSON_TYPE, CommonResponse.respFailJson("9999","请勿重复添加问题件", params.get("reqNo")), response);
					return ;
				}

			    ProOrderReason proOrderReason = proOrderReasonService.getById(params.get("reason_id")) ;
			    if (proOrderReason==null) {
			    	render(JSON_TYPE, CommonResponse.respFailJson("9999","reason_id错误", params.get("reqNo")), response);
					return ;
				}
			    orderProblem = new OrderProblem() ;
			    orderProblem.setOrderNo(params.get("order_no"));
			    orderProblem.setType(Integer.valueOf(params.get("reason_id")));
			    orderProblem.setReason(params.get("reason"));
			    orderProblem.setCreateTime(nowDate);
			    orderProblem.setStatus(0);
			    orderProblem.setRegistOperate(bossUser.getRealName());
			    orderProblem.setRegistSno(bossUser.getSno());
			    
			    
			    List<String> mime = new ArrayList<String>();
				mime.add("image/jpeg");
				mime.add("image/pjpeg");
				mime.add("image/gif");
				mime.add("image/png");
				List<RequestFile> files = getFile(request, "pro_img",
						configInfo.getFile_root(), "/order/"+ DateUtils.formatDate(nowDate, "yyyyMMdd"),mime);
				if (files.size()>0) {
                  orderProblem.setImg("/slfile"+files.get(0).getFilePath());
				}
                
				orderProblemService.save(orderProblem) ;
				
				//快件进行问题件扫描，原因：电话无人接听，李四，电话13714260001
				OrderTrack orderTrack = new OrderTrack();
				orderTrack.setOrderNo(params.get("order_no"));
				orderTrack.setContext("快件进行问题件扫描，原因："+proOrderReason.getReasonDesc()+","+bossUser.getRealName()+",电话"+bossUser.getPhone());
				orderTrack.setCompleted(0);
				orderTrack.setCreateTime(nowDate);
				orderTrack.setCurrentSubno(bossUser.getSno());
				int id = (int) orderTrackService.save(orderTrack) ;
				
				OrderScan orderScan = new OrderScan() ;
                orderScan.setCarMark(null);
                orderScan.setCreateTime(nowDate);
                orderScan.setOpName(bossUser.getRealName());
                orderScan.setOpSource(1);
                orderScan.setOpType("pro");
                orderScan.setOrderNo(params.get("order_no"));
                orderScan.setSub(bossUser.getSno());
                orderScanService.save(orderScan) ;
				
				render(JSON_TYPE, CommonResponse.respSuccessJson("","上传成功", params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}


	@RequestMapping(value = "reason")	
	public void reason(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				List<Map<String, Object>> data =  proOrderReasonService.listAll() ;
                model.put("data", data) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}



}


