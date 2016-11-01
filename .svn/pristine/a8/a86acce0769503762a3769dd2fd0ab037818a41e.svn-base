package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.OrderVol;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderVolService {

	@Resource
	private Dao dao ;

	
	public long save(OrderVol orderVol) throws SQLException {
		String sql = "insert into order_vol(order_no,vol,`length`,`width`,`height`) values (?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderVol.getOrderNo()) ;
        list.add(orderVol.getVol()) ;
        list.add(orderVol.getLength()) ;
        list.add(orderVol.getWidth()) ;
        list.add(orderVol.getHeight()) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	public void delByOrderNo(String orderNo) throws SQLException {
		 String sql = "delete from  order_vol where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}

}
