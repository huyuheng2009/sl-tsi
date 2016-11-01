package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.OrderProblem;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class OrderProblemService {

	@Resource
	private Dao dao ;

	
	public long save(OrderProblem orderProblem) throws SQLException {
		String sql = "insert into order_problem(order_no,`reason`,`type`,create_time,`status`,regist_operate,regist_sno,`img`) values (?,?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
        list.add(orderProblem.getOrderNo()) ;
        list.add(orderProblem.getReason()) ;
        list.add(orderProblem.getType()) ;
        list.add(DateUtils.formatDate(orderProblem.getCreateTime())) ;
        list.add(orderProblem.getStatus()) ;
        list.add(orderProblem.getRegistOperate()) ;
        list.add(orderProblem.getRegistSno()) ;
        list.add(orderProblem.getImg()) ;
		 return dao.updateGetID(sql, list) ;
	}
	
	public OrderProblem getByOrderNo(String orderNo) throws SQLException {
		String sql = "select p.* from order_problem p where p.order_no=? ";
	    List<Object> list = new ArrayList<Object>();
	    list.add(orderNo);
	    return dao.findFirst(OrderProblem.class, sql, list.toArray()) ;
	}

	public void upImg(String id,String url) throws SQLException {
		String sql = "update order_problem set `img`=? where id=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(url);
		list.add(id);
		dao.update(sql,list.toArray()) ;
	}
	
}
