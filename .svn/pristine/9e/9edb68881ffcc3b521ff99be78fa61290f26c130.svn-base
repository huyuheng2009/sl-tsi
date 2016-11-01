package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderTransport;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderTransportService {

	@Resource
	private Dao dao ;


    public OrderTransport getByCarMark(String carMark,String orderNo) throws SQLException {
    	String sql = "select t.* from order_transport t  where t.car_mark=? and t.order_no=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(carMark) ;
		list.add(orderNo) ;
		return dao.findFirst(OrderTransport.class,sql, list.toArray()) ;
	}
    


}
