package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderPayinfo;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderPayinfoService {

	@Resource
	private Dao dao ;

	
	public long save(OrderPayinfo orderPayinfo) throws SQLException {
		String sql = "insert into order_payinfo(order_id,pay_no,type,resp,amount,fee,params,org_id,create_time) values (?,?,?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderPayinfo.getOrderId()) ;
		list.add(orderPayinfo.getPayNo()) ;
		list.add(orderPayinfo.getType()) ;
		list.add(orderPayinfo.getResp()) ;
		list.add(orderPayinfo.getAmount()) ;
		list.add(orderPayinfo.getFee()) ;
		list.add(orderPayinfo.getParams()) ;
		list.add(orderPayinfo.getOrgId()) ;
		list.add(DateUtils.formatDate(orderPayinfo.getCreateTime())) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	public void updateOrderId(String orderId,String id) throws SQLException {
		String sql = "update order_payinfo set order_id=? where id=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderId) ;
		list.add(id) ;
		 dao.updateGetID(sql, list) ;
	}
	
	public void callBackUpdate(OrderPayinfo orderPayinfo) throws SQLException {
		String sql = "update order_payinfo set trade_state=?,amount=?,realfee=?,merchant_id=?,notice_no=?,sign=?,pay_time=?,pay_order_id=? where pay_no=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderPayinfo.getTradeState()) ;
		list.add(orderPayinfo.getAmount()) ;
		list.add(orderPayinfo.getRealfee()) ;
		list.add(orderPayinfo.getMerchantId()) ;
		list.add(orderPayinfo.getNoticeNo()) ;
		list.add(orderPayinfo.getSign()) ;
		list.add(DateUtils.formatDate(orderPayinfo.getPayTime())) ;
		list.add(orderPayinfo.getPayOrderId()) ;
		list.add(orderPayinfo.getPayNo()) ;
		 dao.updateGetID(sql, list) ;
	}
	
	public OrderPayinfo getById(String id) throws SQLException {
		String sql = "select o.* from order_payinfo o where o.id=? ";
	    List<Object> list = new ArrayList<Object>();
	    list.add(id);
	    return dao.findFirst(OrderPayinfo.class, sql, list.toArray()) ;
	}
	
	public OrderPayinfo getByPayNo(String payNo) throws SQLException {
		String sql = "select o.* from order_payinfo o where o.pay_no=? ";
	    List<Object> list = new ArrayList<Object>();
	    list.add(payNo);
	    return dao.findFirst(OrderPayinfo.class, sql, list.toArray()) ;
	}
	

	public void delById(Integer id) throws SQLException {
		 String sql = "delete from  order_payinfo where id=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(id) ;
		  dao.update(sql, list.toArray()) ;
	}

}
