package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.Config;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class ConfigService {

	@Resource
	private Dao dao ;

	
	public Config getByKey(String key) throws SQLException {
		String sql = "select * from config where `key`=?  and `status`=1";
		List<Object> list = new ArrayList<Object>();
		list.add(key);
		return dao.findFirst(Config.class, sql, list.toArray()) ;
	}


}
