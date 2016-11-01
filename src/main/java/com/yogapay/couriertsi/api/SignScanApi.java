package com.yogapay.couriertsi.api;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.CourierPay;
import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderPayinfo;
import com.yogapay.couriertsi.domain.OrderScan;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigService;
import com.yogapay.couriertsi.services.CourierPayService;
import com.yogapay.couriertsi.services.OrderPackService;
import com.yogapay.couriertsi.services.OrderPayinfoService;
import com.yogapay.couriertsi.services.OrderProblemService;
import com.yogapay.couriertsi.services.OrderScanService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.services.OrderTrackService;
import com.yogapay.couriertsi.services.PackService;
import com.yogapay.couriertsi.services.SubstationService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.StringUtils;


@Controller
@RequestMapping(value="/signscan")
@Scope("prototype")
public class SignScanApi extends BaseApi{
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private OrderService orderService  ;
	@Resource
	private OrderProblemService orderProblemService ;
	@Resource
	private PackService packService  ;
	@Resource
	private OrderPackService orderPackService ;
	@Resource
	private ConfigService configService ;
	@Resource
	private SubstationService substationService ;
	@Resource
	private OrderTrackService orderTrackService ;
	@Resource private OrderScanService orderScanService ;
	@Resource private OrderPayinfoService orderPayinfoService ;
	@Resource private CourierPayService courierPayService ;
	 

	@RequestMapping(value = "sign")	
	public void sign(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_no","signer"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                BossUser bossUser = bossUserService.getByUserName(userName) ;

                Date nowDate = new Date() ;
                
                OrderInfo orderInfo = orderService.getByOrderNo(params.get("order_no")) ;
                if (orderInfo==null) {
			    	render(JSON_TYPE, CommonResponse.respFailJson("9999","运单号不存在", params.get("reqNo")), response);
					return ;
				}
                if (orderInfo.getStatus()==5) {
                	render(JSON_TYPE, CommonResponse.respFailJson("9999","该运单号已经签收", params.get("reqNo")), response);
					return ;
				}
                if (orderInfo.getStatus()!=2&&orderInfo.getStatus()!=3&&orderInfo.getStatus()!=4) {
                	render(JSON_TYPE, CommonResponse.respFailJson("9999","运单状态不正确", params.get("reqNo")), response);
					return ;
				}
                if (!bossUser.getSno().equals(orderInfo.getSendSno())) {
                	render(JSON_TYPE, CommonResponse.respFailJson("9999","当前站点和目的地网点不符", params.get("reqNo")), response);
					return ;
				}
                CourierPay courierPay = new CourierPay() ;
                if (orderInfo.getSendMoney()>0) {
				   if (StringUtils.isEmptyWithTrim(params.get("freight_pay_type"))) {
					    render(JSON_TYPE, CommonResponse.respFailJson("9999","还有费用需要收取，请选择支付方式", params.get("reqNo")), response);
						return ;     
				   }
				   if (Integer.valueOf(params.get("freight_pay_type"))!=1&&Integer.valueOf(params.get("freight_pay_type"))!=2) {
					    render(JSON_TYPE, CommonResponse.respFailJson("9999","请选择正确的支付方式", params.get("reqNo")), response);
						return ;   
					}
				   if (orderInfo.getFreightType()==2) {
					   orderInfo.setFreightPayType(Integer.valueOf(params.get("freight_pay_type")));
					   orderInfo.setFreightPayStatus(1);
				   }
				   
				    orderInfo.setBehalfPayStatus(1);
					orderInfo.setBehalfPayType(orderInfo.getFreightPayType());
				   
					courierPay.setOrderId(orderInfo.getId());
					courierPay.setCourierNo(bossUser.getUserName()); 
                	courierPay.setCreateTime(nowDate);
                	courierPay.setRevMoney(orderInfo.getSendMoney());
                	courierPay.setPayMoney(orderInfo.getSendMoney());
                	
                	courierPay.setType(2);
                	if (orderInfo.getFreightType()==2) {
                		courierPay.setFreight(orderInfo.getSendMoney()-orderInfo.getBehalf());
	                	courierPay.setInsuredFee(orderInfo.getInsuredFee());
					}else {
						courierPay.setFreight(0.0);
	                	courierPay.setInsuredFee(0.0);
					}
                	
                	courierPay.setBehalf(orderInfo.getBehalf());
					
				   if (orderInfo.getFreightPayType()==1) {
						//现金
					   if (orderInfo.getFreightType()==2) {
						   orderInfo.setFreightSettleStatus(0);
					    }
	                	
	                	orderInfo.setBehalfSettleStatus(0);
	                	courierPay.setIsPay(0);
	                	
	                	
					}else {
						//刷卡
						
						courierPay.setIsPay(1);
						
						
						 if (orderInfo.getFreightType()==2) {
							 orderInfo.setFreightSettleStatus(1);
								orderInfo.setFreightSettleTime(nowDate);
						    }
						orderInfo.setBehalfSettleStatus(1);
						orderInfo.setBehalfSettleTime(nowDate);
						
						 if (StringUtils.isEmptyWithTrim(params.get("pay_no"))) {
							    render(JSON_TYPE, CommonResponse.respFailJson("9001","缺少刷卡支付pay_no", params.get("reqNo")), response);
								return ;     
						 }
						 if (orderInfo.getFreightType()==2&&orderInfo.getFreightPayStatus()==1) {
	                		 render(JSON_TYPE, CommonResponse.respFailJson("9999","订单错误", params.get("reqNo")), response);
	 					    return ;
	 				     }
						 if (orderInfo.getBehalfPayStatus()==1) {
	                		 render(JSON_TYPE, CommonResponse.respFailJson("9999","订单错误", params.get("reqNo")), response);
	 					    return ;
	 				     } 
						 
						OrderPayinfo orderPayinfo = orderPayinfoService.getByPayNo(params.get("pay_no")) ;
						if (orderPayinfo==null) {
							render(JSON_TYPE, CommonResponse.respFailJson("9999","pay_no错误", params.get("reqNo")), response);
	 					    return ;
						}
						 
						if (orderPayinfo.getType()!=2||orderPayinfo.getOrderId()!=orderInfo.getId()) {
							render(JSON_TYPE, CommonResponse.respFailJson("9999","pay_no错误", params.get("reqNo")), response);
	 					    return ;
						}
						
						if (orderPayinfo.getTradeState()!=1) {
							render(JSON_TYPE, CommonResponse.respFailJson("9999","刷卡状态未成功，请稍后再试", params.get("reqNo")), response);
	 					    return ;
						}

					}
			    }
               orderInfo.setStatus(5); 
               orderInfo.setSigner(params.get("signer"));
               orderInfo.setSendCourierNo(bossUser.getUserName());
               orderInfo.setSendSno(bossUser.getSno());
               orderInfo.setSignTime(nowDate);
               orderService.signUpdate(orderInfo);
               
               if (orderInfo.getSendMoney()>0) {
                 	courierPayService.save(courierPay) ;
				}

				// 已签收，签收人：XXX，感谢使用善良货运
				OrderTrack orderTrack = new OrderTrack();
				orderTrack.setOrderNo(params.get("order_no"));
				orderTrack.setContext("已签收，签收人："+params.get("signer")+"，感谢使用善良货运");
				orderTrack.setCompleted(0);
				orderTrack.setCreateTime(nowDate);
				orderTrack.setCurrentSubno(bossUser.getSno());
				int id = (int) orderTrackService.save(orderTrack) ;
				
				
				OrderScan orderScan = new OrderScan() ;
                orderScan.setCarMark(null);
                orderScan.setCreateTime(nowDate);
                orderScan.setOpName(bossUser.getRealName());
                orderScan.setOpSource(1);
                orderScan.setOpType("sign");
                orderScan.setOrderNo(params.get("order_no"));
                orderScan.setSub(bossUser.getSno());
                orderScanService.save(orderScan) ;
				

				render(JSON_TYPE, CommonResponse.respSuccessJson("","签收成功", params.get("reqNo")), response);   			
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


