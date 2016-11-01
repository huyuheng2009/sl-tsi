package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.Substation;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class SubstationService {

	@Resource
	private Dao dao ;

	
	public List<Map<String, Object>> getByArea(String province_id,String city_id,String area_id) throws SQLException {
		String sql = "select s.substation_no,s.substation_name from substation s where s.`level`=4 and s.province_id=? and s.city_id=? and s.area_id=? limit 0,50";
		List<Object> list = new ArrayList<Object>();
		list.add(province_id) ;
		list.add(city_id) ;
		list.add(area_id) ;
		return dao.find(sql, list.toArray()) ;
	}

	public Substation getBySno(String sno) throws SQLException {
		String sql = "select s.* from substation s where s.`substation_no`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(sno) ;
		return dao.findFirst(Substation.class,sql, list.toArray()) ;
	}
	
	public Substation getByLevel(Integer level) throws SQLException {
		String sql = "select s.* from substation s where s.`level`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(level) ;
		return dao.findFirst(Substation.class,sql, list.toArray()) ;
	}

}
