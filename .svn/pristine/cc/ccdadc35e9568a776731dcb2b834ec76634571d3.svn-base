package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class PackConfigService {

	@Resource
	private Dao dao ;

	
	public List<PackConfig> listAll() throws SQLException {
		String sql = "select * from pack_config where  `status`=1 limit 0,200";
		List<Object> list = new ArrayList<Object>();
		return dao.find(PackConfig.class, sql, list.toArray()) ;
	}


}
