package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.SubstationMerchant;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class SubstationMerchantService {

	@Resource
	private Dao dao ;

	public SubstationMerchant getBySubno(String subno) throws SQLException {
		String sql = "select * from substation_merchant  where subno=? ";
	    List<Object> list = new ArrayList<Object>();
	    list.add(subno);
	    return dao.findFirst(SubstationMerchant.class, sql, list.toArray()) ;
	}

}
