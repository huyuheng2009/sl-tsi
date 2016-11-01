package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.BillConfig;
import com.yogapay.couriertsi.domain.OrderReamrk;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderRemarkService {

	@Resource
	private Dao dao ;

	
	public void save(OrderReamrk reamrk) throws SQLException {
		String sql = "insert into order_reamrk(order_id,remark,operate,real_name,create_time) values (?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(reamrk.getOrderId());
		list.add(reamrk.getRemark());
		list.add(reamrk.getOperate()) ;
		list.add(reamrk.getRealName()) ;
		list.add(DateUtils.formatDate(reamrk.getCreateTime())) ;
	    dao.update(sql, list.toArray()) ;
	}


}
