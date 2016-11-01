package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.BehalfPay;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class BehalfPayService {

	@Resource
	private Dao dao ;

	
	public long save(BehalfPay behalfPay) throws SQLException {
		String sql = "insert into behalf_pay(bank,bank_code,bank_user_name,bank_acct,order_no,money,is_pay) values (?,?,?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(behalfPay.getBank());
		list.add(behalfPay.getBankCode());
		list.add(behalfPay.getBankUserName());
		list.add(behalfPay.getBankAcct());
		list.add(behalfPay.getOrderNo());
		list.add(behalfPay.getMoney());
		list.add(behalfPay.getIsPay());
		delByOrderNo(behalfPay.getOrderNo()) ;
		 return dao.updateGetID(sql, list) ;
	}

	public void delByOrderNo(String orderNo) throws SQLException {
		 String sql = "delete from  behalf_pay where order_no=? ";
		 List<Object> list = new ArrayList<Object>();
		 list.add(orderNo) ;
		  dao.update(sql, list.toArray()) ;
	}

}
