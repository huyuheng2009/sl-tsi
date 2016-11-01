package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderAddr;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderAddrService {

	@Resource
	private Dao dao ;

	
	public List<Map<String, Object>> getBySendName(String sendName) throws SQLException {
		String sql = "select distinct d.send_name,d.send_card,d.send_tell,d.send_province_id,d.send_city_id,"
                     +"d.send_area_id,d.send_street_id,d.send_address,d.send_detail "
		             +" from order_addr d where d.send_name=? limit 0,50";
		List<Object> list = new ArrayList<Object>();
		list.add(sendName) ;
		return dao.find( sql, list.toArray()) ;
	}

	
	public List<Map<String, Object>> getBySendTell(String sendTell) throws SQLException {
		String sql = "select distinct d.send_name,d.send_card,d.send_tell,d.send_province_id,d.send_city_id,"
                     +"d.send_area_id,d.send_street_id,d.send_address,d.send_detail "
		             +" from order_addr d where d.send_tell=? limit 0,50";
		List<Object> list = new ArrayList<Object>();
		list.add(sendTell) ;
		return dao.find( sql, list.toArray()) ;
	}

	public long save(OrderAddr orderAddr) throws SQLException {
		String sql = "insert into order_addr(vip_code,order_id,send_name,send_card,send_tell,send_province_id,send_city_id,"
				+ "send_area_id,send_street_id,send_address,send_detail,rev_name,rev_tell,rev_province_id,rev_city_id,"
				+ "rev_area_id,rev_street_id,rev_address,rev_detail) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderAddr.getVipCode()) ;
        list.add(orderAddr.getOrderId()) ;
        list.add(orderAddr.getSendName()) ;
        list.add(orderAddr.getSendCard()) ;
        list.add(orderAddr.getSendTell()) ;
        list.add(orderAddr.getSendProvinceId()) ;
        list.add(orderAddr.getSendCityId()) ;
        list.add(orderAddr.getSendAreaId()) ;
        list.add(orderAddr.getSendStreetId()) ;
        list.add(orderAddr.getSendAddress()) ;
        list.add(orderAddr.getSendDetail()) ;
        list.add(orderAddr.getRevName()) ;
        list.add(orderAddr.getRevTell()) ;
        list.add(orderAddr.getRevProvinceId()) ;
        list.add(orderAddr.getRevCityId()) ;
        list.add(orderAddr.getRevAreaId()) ;
        list.add(orderAddr.getRevStreetId()) ;
        list.add(orderAddr.getRevAddress()) ;
        list.add(orderAddr.getRevDetail()) ;
        delByOrderId(orderAddr.getOrderId()) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	
	public void delByOrderId(int orderId) throws SQLException {
		 String sql = "delete from  order_addr where order_id=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderId) ;
		  dao.update(sql, list.toArray()) ;
	}
	
}
