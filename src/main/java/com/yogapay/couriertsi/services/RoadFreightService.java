package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.PackConfig;
import com.yogapay.couriertsi.utils.Dao;
import com.yogapay.couriertsi.utils.Kyfw12306Util;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class RoadFreightService {

	@Resource
	private Dao dao ;

	
	public float getFreight(String weight,String itemName,String sendSataion,String revStation) throws SQLException {
		String sql = "select r.* from road_freight r where r.weight=? and r.item_name=? and r.send_sataion=? and r.rev_station=? limit 0,1";
		List<Object> list = new ArrayList<Object>();
		list.add(weight) ;
		list.add(itemName) ;
		list.add(sendSataion) ;
		list.add(revStation) ;
		Map<String, Object> map =  dao.findFirst(sql, list.toArray()) ;
		float ret = 0 ;
		if (map==null||map.get("zfy")==null) {
			ret = Kyfw12306Util.simpleGet(weight, itemName, sendSataion, revStation) ;
			if (ret>0) {
				String insert = "insert into road_freight(weight,item_name,send_sataion,rev_station,zfy) values (?,?,?,?,?)" ;
				List<Object> list1 = new ArrayList<Object>(list);
				list1.add(ret) ;
				dao.update(insert, list1.toArray()) ;
			}else {
				String rail = "select r.* from rail_config r  where ?>=r.min_m and ?<r.max_m order by r.id desc " ;
				List<Object> list2 = new ArrayList<Object>();
				list2.add(weight) ;
				list2.add(weight) ;
				Map<String, Object> railMap = dao.findFirst(rail, list2.toArray()) ;
				if (railMap!=null&&railMap.get("fee")!=null) {
					ret = Float.valueOf(railMap.get("fee").toString()) ;
				}
			}
		}else {
			ret = Float.valueOf(map.get("zfy").toString()) ;
		}
		return ret;
	}


}
