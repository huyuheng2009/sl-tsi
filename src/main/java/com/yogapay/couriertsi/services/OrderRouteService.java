package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderRoute;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderRouteService {

	@Resource
	private Dao dao ;


	public long save(OrderRoute orderRoute) throws SQLException {
		String sql = "insert into order_route(order_no,sub1,sub2,sub3,sub4,sub5,sub6,sub_route) values (?,?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderRoute.getOrderNo());
		list.add(orderRoute.getSub1());
		list.add(orderRoute.getSub2());
		list.add(orderRoute.getSub3());
		list.add(orderRoute.getSub4());
		list.add(orderRoute.getSub5());
		list.add(orderRoute.getSub6());
		list.add(orderRoute.getSubRoute());
		delByNo(orderRoute.getOrderNo());
		 return dao.updateGetID(sql, list) ;
	}
	
	public void upFreightProfit(OrderRoute orderRoute) throws SQLException {
		String sql = "update order_route set sub_normal_profit=?,sub1_profit=?,sub2_profit=?,sub5_profit=?,sub6_profit=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(orderRoute.getSubNormalProfit());
		list.add(orderRoute.getSub1Profit());
		list.add(orderRoute.getSub2Profit());
		list.add(orderRoute.getSub5Profit());
		list.add(orderRoute.getSub6Profit());
		list.add(orderRoute.getId());
		 dao.updateGetID(sql, list) ;
	}
	
	
	public void upFreightProfitByNo(OrderRoute orderRoute) throws SQLException {
		String sql = "update order_route set sub_normal_profit=?,sub1_profit=?,sub2_profit=?,sub5_profit=?,sub6_profit=? where order_no=?";
		List<Object> list = new ArrayList<Object>();
		list.add(orderRoute.getSubNormalProfit());
		list.add(orderRoute.getSub1Profit());
		list.add(orderRoute.getSub2Profit());
		list.add(orderRoute.getSub5Profit());
		list.add(orderRoute.getSub6Profit());
		list.add(orderRoute.getOrderNo());
		 dao.updateGetID(sql, list) ;
	}
	
	public OrderRoute getById(String routeId) throws SQLException {
		String sql = "select r.* from order_route r where r.id=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(routeId) ;
		return dao.findFirst(OrderRoute.class,sql, list.toArray()) ;
	}

	
	public OrderRoute getByOrderNo(String orderNo) throws SQLException {
		String sql = "select r.* from order_route r where r.order_no=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderNo) ;
		return dao.findFirst(OrderRoute.class,sql, list.toArray()) ;
	}
	
	public void delByNo(String orderNo) throws SQLException {
		 String sql = "delete from  order_route where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}

}
