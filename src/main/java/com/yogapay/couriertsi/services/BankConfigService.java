package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.GoodConfig;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class BankConfigService {

	@Resource
	private Dao dao ;

	
	public List<Map<String, Object>> listAll() throws SQLException {
		String sql = "select id AS code,bank_name from bank_config limit 0,200";
		List<Object> list = new ArrayList<Object>();
		return dao.find(sql, list.toArray()) ;
	}


}
