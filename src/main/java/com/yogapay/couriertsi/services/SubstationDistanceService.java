package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.RoadConfig;
import com.yogapay.couriertsi.domain.SubstationDistance;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class SubstationDistanceService {

	@Resource
	private Dao dao ;

	
	
	public SubstationDistance getBySub(String from_substation_no,String to_substation_no) throws SQLException {
		String sql = "select d.* from substation_distance d where d.from_substation_no=? and d.to_substation_no=?";
		List<Object> list = new ArrayList<Object>();
		list.add(from_substation_no) ;
		list.add(to_substation_no) ;
		return dao.findFirst(SubstationDistance.class,sql, list.toArray()) ;
	}


}
