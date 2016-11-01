package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.BillConfig;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class BillConfigService {

	@Resource
	private Dao dao ;

	
	public List<BillConfig> listAll() throws SQLException {
		String sql = "select * from bill_config where  `status`=1 limit 0,200";
		List<Object> list = new ArrayList<Object>();
		return dao.find(BillConfig.class, sql, list.toArray()) ;
	}


}
