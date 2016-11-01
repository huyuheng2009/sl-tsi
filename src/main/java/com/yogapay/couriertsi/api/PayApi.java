package com.yogapay.couriertsi.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderPayinfo;
import com.yogapay.couriertsi.services.CourierPayService;
import com.yogapay.couriertsi.services.OrderPayinfoService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.task.SettlePushTask;
import com.yogapay.couriertsi.utils.JsonUtil;
import com.yogapay.couriertsi.utils.RSAHelper;
import com.yogapay.couriertsi.utils.StringUtils;


@Controller
@RequestMapping(value="/pay")
@Scope("prototype")
public class PayApi extends BaseApi{
	
	@Resource private OrderPayinfoService orderPayinfoService ;
	@Resource private OrderService orderService ;
	@Resource private CourierPayService courierPayService ;
	@Resource private SettlePushTask settlePushTask ;

	@RequestMapping(value = "callback")	
	public void callback(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		String xrealip = request.getHeader("x-real-ip");
		String xforwarded = request.getHeader("x-forwarded-for");
		Date nowDate = new Date() ;
		System.out.println("callback-----------------"+xrealip+"-----------"+xforwarded);
		Map<String, Object> retMap = new HashMap<String, Object>() ;
		retMap.put("code", "0000");
		retMap.put("msg", "success");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder requestBody = new StringBuilder();
			String line = null;
	        while((line = br.readLine())!=null){
	        	requestBody.append(line);
	        }
	        System.out.println(requestBody);
	        
	        String body = JsonUtil.getProperty(requestBody.toString(), "body").toString() ;
	        String sign = JsonUtil.getProperty(requestBody.toString(), "sign").toString() ;
	        System.out.println("body="+body);
	        System.out.println("sygn="+sign);
	    	RSAHelper rsaHelper = new RSAHelper();
			rsaHelper.testMe(configInfo.getNotice_public_key());
			boolean s = rsaHelper.verifyRSA(body.getBytes(), sign.getBytes(), true, "utf-8") ;
			System.out.println(s+"-----------------------sign");
	        if (s) {
	        	Map<String, Object> bMap = JsonUtil.getMapFromJson(body) ;
	        	String pay_order_id = bMap.get("orderId")==null?"":bMap.get("orderId").toString() ;
	        	Integer tradeState = Integer.valueOf(bMap.get("tradeState").toString()) ;
	        	Integer amount = Integer.valueOf(bMap.get("amount").toString()) ;
	        	Integer fee = Integer.valueOf(bMap.get("fee").toString()) ;
	        	String p = bMap.get("params")==null?"":bMap.get("params").toString() ;
	        	String orgId = bMap.get("orgId")==null?"":bMap.get("orgId").toString() ;
	        	String merchantId = bMap.get("merchantId")==null?"":bMap.get("merchantId").toString() ;
	        	String pay_no = bMap.get("merchantOrderId")==null?"":bMap.get("merchantOrderId").toString() ;
	        	String noticeNo = bMap.get("noticeNo").toString() ;

                OrderPayinfo orderPayinfo = orderPayinfoService.getByPayNo(pay_no) ;
                if (orderPayinfo==null) {
                	retMap.put("code", "9000");
    				retMap.put("msg", "params error");
				}else {
					
					
					if (!StringUtils.isEmptyWithTrim(orderPayinfo.getNoticeNo())) {
						retMap.put("code", "0000");
						retMap.put("msg", "success");
						render(JSON_TYPE, JsonUtil.toJson(retMap), response);
						return ;
					}
					
					orderPayinfo.setPayOrderId(pay_order_id);
		        	orderPayinfo.setTradeState(tradeState);
		        	orderPayinfo.setAmount(amount);
		        	orderPayinfo.setRealfee(fee);
		        	orderPayinfo.setMerchantId(merchantId);
		        	orderPayinfo.setNoticeNo(noticeNo);
		        	orderPayinfo.setSign(sign);
		        	orderPayinfo.setPayTime(nowDate);
		        	orderPayinfoService.callBackUpdate(orderPayinfo);
		        	
		        	
		       if (orderPayinfo.getType()==1) {
		        		OrderInfo orderInfo = new OrderInfo() ;
			        	orderInfo.setId(orderPayinfo.getOrderId());
			        	
    				  if (orderPayinfo.getTradeState()==1) {
    		        		orderInfo.setFreightSettleStatus(1);
        					orderInfo.setFreightSettleTime(nowDate);
        					orderInfo.setFreightPayStatus(1);
        					orderInfo.setStatus(2);
        					orderService.paySuUpdate(orderInfo);
					  }else {
						orderInfo.setStatus(1);
						orderInfo.setFreightPayStatus(0);
						orderService.payFailUpdate(orderInfo);
					}
		        } 	
		        	if (orderPayinfo.getType()==3) {
		        		if (orderPayinfo.getTradeState()==1) {
		        			courierPayService.updatePayStatus(pay_no, 1, nowDate);
		        			orderService.paySettleUpdate(pay_no);
						}else {
							courierPayService.updatePayStatus(pay_no, 0, nowDate);
						}
					}
				}
			}else {
				retMap.put("code", "9000");
				retMap.put("msg", "sign error");
			}
			System.out.println("----------------------------------callback-end");
			render(JSON_TYPE, JsonUtil.toJson(retMap), response);
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("code", "9000");
			retMap.put("msg", "fail");
			render(JSON_TYPE, JsonUtil.toJson(retMap), response);
		}
	}






}


