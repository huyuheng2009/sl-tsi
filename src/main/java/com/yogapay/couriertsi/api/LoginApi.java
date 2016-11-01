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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BillConfig;
import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.Config;
import com.yogapay.couriertsi.domain.GoodConfig;
import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.domain.UserSession;
import com.yogapay.couriertsi.services.BankConfigService;
import com.yogapay.couriertsi.services.BillConfigService;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigService;
import com.yogapay.couriertsi.services.GoodConfigService;
import com.yogapay.couriertsi.services.PackConfigService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.DateUtils;
import com.yogapay.couriertsi.utils.RequestFile;


@Controller
@RequestMapping(value="/login")
@Scope("prototype")
public class LoginApi extends BaseApi{
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private ConfigService configService ;
	@Resource
	private GoodConfigService goodConfigService ;
	@Resource
	private PackConfigService packConfigService ;
	@Resource
	private BillConfigService billConfigService ;
	@Resource
	private BankConfigService bankConfigService ;
	

	@RequestMapping(value = "")	
	public void login(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, String> ret = validateRequest(request,new String[] { "user_name", "pass_word"}, false);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				BossUser user = bossUserService.getByPwd(params.get("user_name"),params.get("pass_word"));

				if (user == null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9002","用户名或密码错误", params.get("reqNo")), response);
					return ;
				}
                 if("0".equals(user.getStatus())){
					render(JSON_TYPE, CommonResponse.respFailJson("9002","当前用户登录权限尚未启用,请联系客服开启登录权限！", params.get("reqNo")), response);	
					return;
				}
                 
                 
                 String session = UUID.randomUUID().toString().replace("-", "");
					UserSession userSession = new UserSession();
					userSession.setUserNo(user.getUserName());
					userSession.setSessionNo(session);
					userSession.setAppVersion(params.get("appVersion"));
					userSession.setAndroidId(params.get("androidId"));
					Date nowDate = new Date();
					userSession.setCreateTime(nowDate);
					userSession.setLastUpdateTime(nowDate);
					userSession.setExpiryTime(DateUtils.addDate(nowDate, 30,12, 0)); // 有效期12小时
					userSession.setIp(getClientIP(request));
					userSessionService.deleteByUserNo(user.getUserName());
					userSessionService.saveCourier(userSession);
					model.put("respMsg", "登陆成功");
                 
                 
                 Map<String, Object> cod_rate = new HashMap<String, Object>() ;
                 Config cod_pay_min = configService.getByKey("cod_pay_min") ;
                 if (cod_pay_min==null) {
					cod_rate.put("cod_pay_min", 0) ;
				  }else {
					  cod_rate.put("cod_pay_min", Double.valueOf(cod_pay_min.getValue())) ;
				  }
                 
                 Config cod_pay_max = configService.getByKey("cod_pay_max") ;
                 if (cod_pay_max==null) {
					cod_rate.put("cod_pay_max", 99999) ;
				  }else {
					  cod_rate.put("cod_pay_max", Double.valueOf(cod_pay_max.getValue())) ;
				  }
                 
                 Config cod_pay_fee = configService.getByKey("cod_pay_fee") ;
                 if (cod_pay_fee==null) {
					cod_rate.put("cod_pay_fee", 0) ;
				  }else {
					  cod_rate.put("cod_pay_fee", Double.valueOf(cod_pay_fee.getValue())) ;
				  }
                 
                 Map<String, Object> valuation_rate = new HashMap<String, Object>() ;
                 Config good_valuation_min = configService.getByKey("good_valuation_min") ;
                 if (good_valuation_min==null) {
                	 valuation_rate.put("good_valuation_min", 0) ;
				  }else {
					  valuation_rate.put("good_valuation_min", Double.valueOf(good_valuation_min.getValue())) ;
				  }
                 
                 Config good_valuation_max = configService.getByKey("good_valuation_max") ;
                 if (good_valuation_max==null) {
                	 valuation_rate.put("good_valuation_max", 99999) ;
				  }else {
					  valuation_rate.put("good_valuation_max", Double.valueOf(good_valuation_max.getValue())) ;
				  }
                 
                 Config good_valuation_fee = configService.getByKey("good_valuation_fee") ;
                 if (good_valuation_fee==null) {
                	 valuation_rate.put("good_valuation_fee", 0) ;
				  }else {
					  valuation_rate.put("good_valuation_fee", Double.valueOf(good_valuation_fee.getValue())) ;
				  }
                 
                 Double vol_weight = 0.0 ;
                  Config volWeight = configService.getByKey("vol_weight") ;
                 if (volWeight!=null) {
					vol_weight = Double.valueOf(volWeight.getValue()) ;
				 }
                 
                 
                  List<String> good_config = new ArrayList<String>() ;
                 List<GoodConfig> goodConfigs =  goodConfigService.listAll() ;
                 for (GoodConfig good:goodConfigs) {
					good_config.add(good.getType()) ;
				}
                 
                 List<String> pack_config = new ArrayList<String>() ;
                 List<PackConfig> packConfigs =  packConfigService.listAll() ;
                 for (PackConfig packConfig:packConfigs) {
                	 pack_config.add(packConfig.getType()) ;
				 } 
                 
                 List<String> bill_config = new ArrayList<String>() ;
                 List<BillConfig> billConfigs =  billConfigService.listAll() ;
                 for (BillConfig billConfig:billConfigs) {
                	 bill_config.add(billConfig.getType()) ;
				 }
                 
                List<Map<String, Object>> bank_config = bankConfigService.listAll() ;
              //  
               List<String> tags = new ArrayList<String>() ; 
               tags.add(configInfo.getTag_fix()+user.getUserName()) ;
               tags.add(configInfo.getTag_fix()+user.getSno()) ;
                 
               model.put("session_no", userSession.getSessionNo()) ;
               model.put("cod_rate", cod_rate) ;
               model.put("valuation_rate", valuation_rate) ;
               model.put("vol_weight", vol_weight) ;
               model.put("good_config", good_config) ;
               model.put("pack_config", pack_config) ;
               model.put("bill_config", bill_config) ;
               model.put("bank_config", bank_config) ;
               model.put("tags", tags) ;
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


