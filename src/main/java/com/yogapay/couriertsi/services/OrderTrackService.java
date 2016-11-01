package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderTrackService {

	@Resource
	private Dao dao ;

	public long save(OrderTrack orderTrack) throws SQLException {
		String sql = "insert into order_track(order_no,context,completed,create_time,current_subno) values (?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderTrack.getOrderNo());
		list.add(orderTrack.getContext()) ;
		list.add(orderTrack.getCompleted()) ;
		list.add(DateUtils.formatDate(orderTrack.getCreateTime())) ;
		list.add(orderTrack.getCurrentSubno()) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	
	public long save(OrderTrack orderTrack,boolean del) throws SQLException {
		String sql = "insert into order_track(order_no,context,completed,create_time,current_subno) values (?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderTrack.getOrderNo());
		list.add(orderTrack.getContext()) ;
		list.add(orderTrack.getCompleted()) ;
		list.add(DateUtils.formatDate(orderTrack.getCreateTime())) ;
		list.add(orderTrack.getCurrentSubno()) ;
		if (del) {
			delByOrderNo(orderTrack.getOrderNo());
		}
		 return dao.updateGetID(sql, list) ;
	}
	
	public void delByOrderNo(String orderNo) throws SQLException {
		 String sql = "delete from  order_track where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}
	
	
	public void delById(Integer id) throws SQLException {
		 String sql = "delete from  order_track where id=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(id) ;
		  dao.update(sql, list.toArray()) ;
	}

}
