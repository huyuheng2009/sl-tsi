package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class GoodConfigService {

	@Resource
	private Dao dao ;

	
	public List<GoodConfig> listAll() throws SQLException {
		String sql = "select * from good_config where  `status`=1 limit 0,200";
		List<Object> list = new ArrayList<Object>();
		return dao.find(GoodConfig.class, sql, list.toArray()) ;
	}


}
