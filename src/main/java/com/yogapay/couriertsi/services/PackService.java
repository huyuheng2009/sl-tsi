package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.GoodConfig;
import com.yogapay.couriertsi.domain.Pack;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class PackService {

	@Resource
	private Dao dao ;

	
	public void save(Pack pack) throws SQLException {
		String sql = "insert into pack(pack_no,create_time,sno,operate,isfee) values (?,?,?,?,?) ";
		List<Object> list = new ArrayList<Object>();
		list.add(pack.getPackNo()) ;
		list.add(DateUtils.formatDate(pack.getCreateTime())) ;
		list.add(pack.getSno()) ;
		list.add(pack.getOperate()) ;
		list.add(pack.getIsfee()) ;
		dao.update(sql, list.toArray()) ;
	}
	
	public void costUpdate(Pack pack) throws SQLException {
		String sql = "update pack set cost=?,fee_time=?,sub3_profit=?,sub_profit=?,isfee=?,`img`=? where pack_no=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(pack.getCost()) ;
		list.add(DateUtils.formatDate(pack.getFeeTime()));
		list.add(pack.getSub3Profit()) ;
		list.add(pack.getSubProfit()) ;
		list.add(pack.getIsfee()) ;
		list.add(pack.getImg()) ;
		list.add(pack.getPackNo()) ;
		dao.update(sql, list.toArray()) ;
	}
	
	
	public Pack getByPackNo(String packNo) throws SQLException {
		String sql = "select * from pack where  `pack_no`=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(packNo) ;
		return dao.findFirst(Pack.class, sql, list.toArray()) ;
	}


}
