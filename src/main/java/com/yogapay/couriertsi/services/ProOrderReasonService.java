package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.ProOrderReason;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class ProOrderReasonService {

	@Resource
	private Dao dao ;

	public List<Map<String, Object>> listAll() throws SQLException {
		String sql = "select r.id as reason_id,r.reason_no,r.reason_desc  from pro_order_reason r where r.`status`=1 limit 0,200";
		List<Object> list = new ArrayList<Object>();
		return dao.find(sql, list.toArray()) ;
	}

	
	public ProOrderReason getById(String id) throws SQLException {
		String sql = "select r.*  from pro_order_reason r where r.`status`=1 and id=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(id) ;
		return dao.findFirst(ProOrderReason.class,sql, list.toArray()) ;
	}

}
