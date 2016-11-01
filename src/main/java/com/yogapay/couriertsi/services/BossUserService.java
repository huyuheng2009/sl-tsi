package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class BossUserService {

	@Resource
	private Dao dao ;

	
	public List<BossUser> listAll() throws SQLException {
		String sql = "select * from boss_user where `status`=1 ";
		List<Object> list = new ArrayList<Object>();
		return dao.find(BossUser.class, sql, list.toArray()) ;
	}
	
	public BossUser getByPwd(String userName,String passWord) throws SQLException {
		String sql = "select * from boss_user where user_name=? and `password`=?";
		List<Object> list = new ArrayList<Object>();
		list.add(userName);
		list.add(passWord) ;
		return dao.findFirst(BossUser.class, sql, list.toArray()) ;
	}

	
	public BossUser getByUserName(String userName) throws SQLException {
		String sql = "select * from boss_user where user_name=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(userName);
		return dao.findFirst(BossUser.class, sql, list.toArray()) ;
	}
	
	public void upwd(String userName,String passWord) throws SQLException {
		String sql = "update boss_user set `password`=? where user_name=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(passWord) ;
		list.add(userName);
		dao.update(sql, list.toArray()) ;
	}
	

}
