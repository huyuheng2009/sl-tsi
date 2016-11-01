package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.Transport;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.DateUtils;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class TransportService {

	@Resource
	private Dao dao ;

	//装车
    public List<Map<String, Object>> getLoadCar(Date dateBegin,Date dateEnd,String sub) throws SQLException {
    	String sql = "select t.car_mark,t.car_card,s.substation_name as sub_name from transport t left join substation s on t.sub_next=s.substation_no "
                     +" where t.`status`=1 and t.isload=0 and t.sub=? and t.start_time between ? and ? order by t.id desc limit 0,50";
		List<Object> list = new ArrayList<Object>();
		list.add(sub) ;
		list.add(DateUtils.formatDate(dateBegin));
		list.add(DateUtils.formatDate(dateEnd));
		return dao.find(sql, list.toArray()) ;
	}
    
    //到车
    public List<Map<String, Object>> getRevCar(Date dateBegin,Date dateEnd,String sub) throws SQLException {
    	String sql = "select t.car_mark,t.car_card,s.substation_name as sub_name from transport t left join substation s on t.sub=s.substation_no "
                     +" where t.`status`=1 and t.isrev=0 and t.sub_next=? and t.start_time between ? and ? order by t.id desc limit 0,50";
		List<Object> list = new ArrayList<Object>();
		list.add(sub) ;
		list.add(DateUtils.formatDate(dateBegin));
		list.add(DateUtils.formatDate(dateEnd));
		return dao.find(sql, list.toArray()) ;
	}
    
    
    public Transport getLoadCarByCarMark(String sub,String carMark) throws SQLException {
    	String sql = "select t.* from transport t  where t.`status`=1 and t.sub=? and t.car_mark=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(sub) ;
		list.add(carMark) ;
		return dao.findFirst(Transport.class,sql, list.toArray()) ;
	}
    
    public Transport getRevCarByCarMark(String sub,String carMark) throws SQLException {
    	String sql = "select t.* from transport t  where t.`status`=1  and t.sub_next=? and t.car_mark=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(sub) ;
		list.add(carMark) ;
		return dao.findFirst(Transport.class,sql, list.toArray()) ;
	}
    
    public void loadUpdateByCarMark(String carMark,int isload) throws SQLException {
    	String sql = "update transport set isload=? where car_mark=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(isload) ;
		list.add(carMark) ;
		dao.update(sql,list.toArray()) ;
	}
    
    public void revUpdateByCarMark(String carMark,int isrev,String end_operate,Date end_time) throws SQLException {
    	String sql = "update transport set isrev=?,end_operate=?,end_time=? where car_mark=?  ";
		List<Object> list = new ArrayList<Object>();
		list.add(isrev) ;
		list.add(end_operate) ;
		list.add(DateUtils.formatDate(end_time));
		list.add(carMark) ;
		dao.update(sql,list.toArray()) ;
	}
    

}
