package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.OrderZidan;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderZidanService {

	@Resource
	private Dao dao ;

	
	public long save(OrderZidan orderZidan,boolean del) throws SQLException {
		String sql = "insert into order_zidan(order_no,zidan_no) values (?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderZidan.getOrderNo()) ;
        list.add(orderZidan.getZidanNo()) ;
        if (del) {
			delByOrderNo(orderZidan.getOrderNo());
		}
		 return dao.updateGetID(sql, list) ;
	}
	
	public void delByOrderNo(String orderNo) throws SQLException {
		 String sql = "delete from  order_zidan where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}
	
	public void delById(Integer id) throws SQLException {
		 String sql = "delete from  order_zidan where id=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(id) ;
		  dao.update(sql, list.toArray()) ;
	}

}
