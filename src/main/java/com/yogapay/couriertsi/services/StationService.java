package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.domain.Route;
import com.yogapay.couriertsi.domain.Station;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class StationService {

	@Resource
	private Dao dao ;


	
	public Station getBySno(String sno) throws SQLException {
		String sql = "select s.* from station s where s.sno=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(sno) ;
		return dao.findFirst(Station.class,sql, list.toArray()) ;
	}


}
