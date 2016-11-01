package com.yogapay.couriertsi.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BillConfig;
import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.Config;
import com.yogapay.couriertsi.domain.GoodConfig;
import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderProblem;
import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.domain.UserSession;
import com.yogapay.couriertsi.services.BankConfigService;
import com.yogapay.couriertsi.services.BillConfigService;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigService;
import com.yogapay.couriertsi.services.GoodConfigService;
import com.yogapay.couriertsi.services.OrderProblemService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.services.PackConfigService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.DateUtils;
import com.yogapay.couriertsi.utils.RequestFile;
import com.yogapay.couriertsi.utils.StringUtils;


@Controller
@RequestMapping(value="/img")
@Scope("prototype")
public class ImgApi extends BaseApi{
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private OrderService orderService  ;
	@Resource
	private OrderProblemService orderProblemService ;
	 

	@RequestMapping(value = "upload")	
	public void upload(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_no","img_type"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                
				OrderInfo orderInfo = orderService.getByOrderNo(params.get("order_no")) ;
				if (orderInfo==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9999","运单不存在", params.get("reqNo")), response);
					return ;
				}
				OrderProblem orderProblem = null ;
				int img_type = 1 ;
				if ("2".equals(params.get("img_type"))) {
					img_type = 2 ;
					orderProblem =	orderProblemService.getByOrderNo(params.get("order_no")) ;
				    if (orderProblem==null) {
				    	render(JSON_TYPE, CommonResponse.respFailJson("9999","当前运单不是问题件", params.get("reqNo")), response);
						return ;
					}
				    if (!StringUtils.isEmptyWithTrim(orderProblem.getImg())) {
						render(JSON_TYPE, CommonResponse.respFailJson("9999","问题件图片已上传过了，请勿重复上传", params.get("reqNo")), response);
						return ;
					}
				}else {
					if (!StringUtils.isEmptyWithTrim(orderInfo.getGoodsImg())) {
						render(JSON_TYPE, CommonResponse.respFailJson("9999","货物图片已上传过了，请勿重复上传", params.get("reqNo")), response);
						return ;
					}
				}
				Date nowDate = new Date() ;
				List<String> mime = new ArrayList<String>();
				mime.add("image/jpeg");
				mime.add("image/pjpeg");
				mime.add("image/gif");
				mime.add("image/png");
				List<RequestFile> files = getFile(request, "img_name",
						configInfo.getFile_root(), "/order/"+ DateUtils.formatDate(nowDate, "yyyyMMdd"),mime);
				if (files.size()<1) {
					render(JSON_TYPE, CommonResponse.respFailJson("9999","至少上传一张图片", params.get("reqNo")), response);
					return ;
				}
				if (img_type==1) {
					orderService.upGoodImg(orderInfo.getId().toString(), "/slfile"+files.get(0).getFilePath());
				}else {
					orderProblemService.upImg(orderProblem.getId().toString(), "/slfile"+files.get(0).getFilePath());
				}
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






}


