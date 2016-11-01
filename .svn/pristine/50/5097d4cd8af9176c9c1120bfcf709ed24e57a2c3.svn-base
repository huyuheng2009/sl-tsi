package com.yogapay.couriertsi.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.utils.CommonResponse;


@Controller
@RequestMapping(value="/room")
@Scope("prototype")
public class RoomApi extends BaseApi{
	@Resource
	private OrderService orderService ;
	@Resource
	private BossUserService bossUserService ;
	

	@RequestMapping(value = "acptlist")	
	public void acptlist(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				Page<Map<String, Object>> pageList = orderService.acptlist(bossUser.getSno(), getPageRequest(cpage)) ;
				Map<String, Object> page_info = new HashMap<String, Object>() ;
				page_info.put("data", pageList.getContent()) ;
				page_info.put("page_no", pageList.getNumber()) ;
				page_info.put("is_last", pageList.isLastPage()) ;

               model.put("page_info", page_info) ;
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

	@RequestMapping(value = "search")	
	public void search(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"keyval"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				Page<Map<String, Object>> pageList = orderService.search(bossUser.getSno(),params.get("keyval"), getPageRequest(cpage)) ;
				Map<String, Object> page_info = new HashMap<String, Object>() ;
				page_info.put("data", pageList.getContent()) ;
				page_info.put("page_no", pageList.getNumber()) ;
				page_info.put("is_last", pageList.isLastPage()) ;

               model.put("page_info", page_info) ;
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


	@RequestMapping(value = "acpt")	
	public void acpt(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_id"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				Map<String, Object> acptMap = orderService.getAcptByOrderId(bossUser.getSno(),params.get("order_id")) ;
				if (acptMap==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9003","订单已经被接", params.get("reqNo")), response);
					return ;
				}
               orderService.updateAcpt(bossUser.getSno(),params.get("order_id"), userName);
				render(JSON_TYPE, CommonResponse.respSuccessJson("","接单成功", params.get("reqNo")), response);   			
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


