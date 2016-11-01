package com.yogapay.couriertsi.task;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.PushMsg;
import com.yogapay.couriertsi.services.BaiduYunPushService;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.ConfigInfo;
import com.yogapay.couriertsi.services.CourierPayService;
import com.yogapay.couriertsi.services.PushMsgService;
import com.yogapay.couriertsi.utils.DateUtils;

@Component
public class SettlePushTask {

	@Resource private BossUserService bossUserService ;
	@Resource private CourierPayService courierPayService ;
	@Resource private PushMsgService pushMsgService ;
	@Resource private ConfigInfo configInfo ;
	@Resource private BaiduYunPushService baiduYunPushService ;
	
	public void start() throws SQLException, PushClientException, PushServerException {
		System.out.println("SettlePushTask-------------start");
		Date nowDate = new Date() ;
		List<BossUser> users = bossUserService.listAll() ;
		for (BossUser bossUser:users) {
			float unsettleMoney = courierPayService.unsettleMoney(bossUser.getUserName()) ;
			if (unsettleMoney>0) {
				PushMsg msg = new PushMsg() ;
				msg.setCreateTime(nowDate);
				msg.setExpireTime(DateUtils.addDate(nowDate, 0, 3, 0));
				msg.setMsgCode("301");
				msg.setMsgContent("当前还有未结算金额："+unsettleMoney+"元，请尽快进行结算");
				msg.setMsgData("");
				msg.setMsgTitle("温馨提示");
                msg.setReaded(0);
                msg.setUserNo(bossUser.getUserName());
                pushMsgService.save(msg);
                baiduYunPushService.pushMsgToTag(configInfo.getTag_fix()+bossUser.getUserName(), msg, 3, 1) ;
			}
		}
	}
	
}
