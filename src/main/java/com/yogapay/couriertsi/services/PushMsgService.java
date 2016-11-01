package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.domain.PushMsg;
import com.yogapay.couriertsi.domain.Route;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class PushMsgService {

	@Resource
	private Dao dao ;

	
	public void save(PushMsg pushMsg) throws SQLException {
		String sql = "insert into push_msg(user_no,msg_code,msg_title,msg_content,msg_data,readed,create_time,expire_time) values (?,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(pushMsg.getUserNo()) ;
		list.add(pushMsg.getMsgCode()) ;
		list.add(pushMsg.getMsgTitle()) ;
		list.add(pushMsg.getMsgContent()) ;
		list.add(pushMsg.getMsgData()) ;
		list.add(pushMsg.getReaded()) ;
		list.add(DateUtils.formatDate(pushMsg.getCreateTime())) ;
		list.add(DateUtils.formatDate(pushMsg.getExpireTime())) ;
		dao.update(sql, list.toArray()) ;
	}
	
	public Integer unreadCount(String userName) throws SQLException {
		String sql = "select count(0) AS unread_count from push_msg p where p.user_no=? and p.readed=0";
		List<Object> list = new ArrayList<Object>();
		list.add(userName) ;
		return Integer.valueOf(dao.findFirst(sql, list.toArray()).get("unread_count").toString()) ;
	}
	
	public Page<Map<String, Object>> listAll(String userName,PageRequest pageRequest) throws SQLException {
		String sql = "select p.id as msg_id,p.msg_code,p.msg_title,p.msg_content,p.msg_data,p.readed,p.create_time as msg_time from push_msg p where p.user_no=? order by p.id desc ";
		List<Object> list = new ArrayList<Object>();
		list.add(userName) ;
		return dao.find(sql, list.toArray(),pageRequest);
	}

    public void msgread(String userName,String ids) throws SQLException {
    	String sql = "update push_msg set readed=true where user_no=?  and  id in ("+ids+")" ;
    	List<Object> list = new ArrayList<Object>();
		list.add(userName) ;
		dao.update(sql, list.toArray()) ;
	}	

    
    public void msgdel(String userName,String ids) throws SQLException {
    	 String sql = "delete from push_msg  where user_no=? and  id in ("+ids+")" ;
    	List<Object> list = new ArrayList<Object>();
		list.add(userName) ;
		dao.update(sql, list.toArray()) ;
	}	
   

}
