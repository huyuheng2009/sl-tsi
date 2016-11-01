package com.yogapay.couriertsi.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yogapay.couriertsi.domain.RoadConfig;
import com.yogapay.couriertsi.utils.Dao;

/**
 * 用户service
 * @author 
 *
 */
@Service
public class RoadConfigService {

	@Resource
	private Dao dao ;

	
	
	public RoadConfig getBySub(String sub1,String sub2) throws SQLException {
		String sql = "select c.* from road_cost r left join road_config c on r.rc_id=c.id where r.sub1=? and r.sub2=?";
		List<Object> list = new ArrayList<Object>();
		list.add(sub1) ;
		list.add(sub2) ;
		return dao.findFirst(RoadConfig.class,sql, list.toArray()) ;
	}
	
	
	public double getCostByWeightDistance(float weight,double distance,RoadConfig roadConfig) {
		double w = weight ;
		double w1 = weight ;
		if (roadConfig.getMinW()-w>0.01) {
			w1 = roadConfig.getMinW() ;
		}
	    double s = roadConfig.getSBYW(w) ;
	    double r = w1/25.0*0+s*w*distance ;
	    double r1 = Math.round(r*100)/100.0 ;
		return  r ;
	}


}
