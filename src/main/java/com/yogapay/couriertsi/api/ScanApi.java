package com.yogapay.couriertsi.api;

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
import com.yogapay.couriertsi.domain.Config;
import com.yogapay.couriertsi.domain.OrderPack;
import com.yogapay.couriertsi.domain.OrderScan;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.Pack;
import com.yogapay.couriertsi.domain.Substation;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigService;
import com.yogapay.couriertsi.services.OrderPackService;
import com.yogapay.couriertsi.services.OrderProblemService;
import com.yogapay.couriertsi.services.OrderScanService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.services.OrderTrackService;
import com.yogapay.couriertsi.services.PackService;
import com.yogapay.couriertsi.services.SubstationService;
import com.yogapay.couriertsi.services.TransportService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.DateUtils;


@Controller
@RequestMapping(value="/scan")
@Scope("prototype")
public class ScanApi extends BaseApi{
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private OrderService orderService  ;


	@Resource private OrderScanService orderScanService ;
	@Resource private TransportService transportService ;
	 

	@RequestMapping(value = "carbanthno")	
	public void carbanthno(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"scan_type"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                BossUser bossUser = bossUserService.getByUserName(userName) ;
                int scan_type = 1 ;
                if ("2".equals(params.get("scan_type"))) {
                	scan_type =2 ;
				}
                Date nowDate = new Date() ;
                Date dateBegin = DateUtils.addDate(nowDate, -10, 0, 0) ;
                List<Map<String, Object>> data = null ;
                if (1==scan_type) {
					data = transportService.getLoadCar(dateBegin, nowDate, bossUser.getSno()) ;
				}else {
					data = transportService.getRevCar(dateBegin, nowDate, bossUser.getSno()) ;
				}
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


