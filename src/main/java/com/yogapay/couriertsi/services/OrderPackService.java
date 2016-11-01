package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.GoodConfig;
import com.yogapay.couriertsi.domain.OrderPack;
import com.yogapay.couriertsi.domain.Pack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderPackService {

	@Resource
	private Dao dao ;

	
	public void save(OrderPack orderPack) throws SQLException {
		String sql = "insert into order_pack(order_no,track_id,pack_no,create_time,status) values (?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderPack.getOrderNo()) ;
		list.add(orderPack.getTrackId()) ;
		list.add(orderPack.getPackNo()) ;
		list.add(DateUtils.formatDate(orderPack.getCreateTime())) ;
		list.add(orderPack.getStatus()) ;
		dao.update(sql, list.toArray()) ;
	}
	
	public OrderPack getByOrderNo(String orderNo) throws SQLException {
		String sql = "select * from order_pack where  `order_no`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(orderNo) ;
		return dao.findFirst(OrderPack.class, sql, list.toArray()) ;
	}
	
	public List<String> getOrderNoByPackNo(String packNo) throws SQLException {
		String sql = "select order_no from order_pack where  `pack_no`=? limit 0,200";
		List<Object> list = new ArrayList<Object>();
		list.add(packNo) ;
		List<Map<String, Object>> ret = dao.find(sql, list.toArray()) ;
		List<String> oList = new ArrayList<String>() ;
		for (Map<String, Object> map:ret) {
			oList.add(map.get("order_no").toString()) ;
		}
		return oList ;
	}
	
	public float getPackOrderCost(String packNo) throws NumberFormatException, SQLException {
		String sql = "select IFNULL(sum(o.rail_cost),0) AS max_cost,count(0) AS c  from order_pack p left join order_info o on p.order_no=o.order_no where  p.`pack_no`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(packNo) ;
		Map<String, Object> retMap = dao.findFirst(sql, list.toArray()) ;
		if (Integer.valueOf(retMap.get("c").toString())==1) {
			return (float) 9999999.99 ;
		}
		return Float.valueOf(retMap.get("max_cost").toString()) ;
	}

	
	public void delByOrderNo(String orderNo) throws SQLException {
		String sql = "delete from  order_pack where `order_no`=?";
		List<Object> list = new ArrayList<Object>();
		list.add(orderNo) ;
		dao.update(sql, list.toArray()) ;
	}
	

}
