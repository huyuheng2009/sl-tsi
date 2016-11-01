package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.domain.RateConfig;
import com.yogapay.couriertsi.domain.Route;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class RateConfigService {

	@Resource
	private Dao dao ;

	
	public RateConfig getByWeightType(float weight,int type) throws SQLException {
		String sql = "select r.* from rate_config r where ?>=r.minv and r.maxv>=? and r.`type`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(weight) ;
		list.add(weight) ;
		list.add(type) ;
		return dao.findFirst(RateConfig.class,sql, list.toArray()) ;
	}
	
	public RateConfig getByRWeightType(int type) throws SQLException {
		String sql = "select r.* from rate_config r where r.`type`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(type) ;
		return dao.findFirst(RateConfig.class,sql, list.toArray()) ;
	}


}
