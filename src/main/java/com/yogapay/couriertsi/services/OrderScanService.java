package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderScan;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderScanService {

	@Resource
	private Dao dao ;

	
	public long save(OrderScan orderScan) throws SQLException {
		String sql = "insert into order_scan(car_mark,order_no,sub,op_type,op_source,op_name,create_time) values (?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderScan.getCarMark()) ;
        list.add(orderScan.getOrderNo()) ;
        list.add(orderScan.getSub()) ;
        list.add(orderScan.getOpType());
        list.add(orderScan.getOpSource()) ;
        list.add(orderScan.getOpName()) ;
        list.add(DateUtils.formatDate(orderScan.getCreateTime())) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	public long save(OrderScan orderScan,boolean del) throws SQLException {
		String sql = "insert into order_scan(car_mark,order_no,sub,op_type,op_source,op_name,create_time) values (?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderScan.getCarMark()) ;
        list.add(orderScan.getOrderNo()) ;
        list.add(orderScan.getSub()) ;
        list.add(orderScan.getOpType());
        list.add(orderScan.getOpSource()) ;
        list.add(orderScan.getOpName()) ;
        list.add(DateUtils.formatDate(orderScan.getCreateTime())) ;
        if (del) {
			delByOrderNo(orderScan.getOrderNo());
		}
		 return dao.updateGetID(sql, list) ;
	}
	
	public void delByOrderNo(String orderNo) throws SQLException {
		 String sql = "delete from  order_scan where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}
	
	public void delById(Integer id) throws SQLException {
		 String sql = "delete from  order_scan where id=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(id) ;
		  dao.update(sql, list.toArray()) ;
	}

}
