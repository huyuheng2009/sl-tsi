package com.yogapay.couriertsi.api;

import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yogapay.couriertsi.domain.BehalfPay;
import com.yogapay.couriertsi.domain.BossUser;
import com.yogapay.couriertsi.domain.CourierPay;
import com.yogapay.couriertsi.domain.OrderAddr;
import com.yogapay.couriertsi.domain.OrderInfo;
import com.yogapay.couriertsi.domain.OrderPayinfo;
import com.yogapay.couriertsi.domain.OrderPrepay;
import com.yogapay.couriertsi.domain.OrderReamrk;
import com.yogapay.couriertsi.domain.OrderRoute;
import com.yogapay.couriertsi.domain.OrderScan;
import com.yogapay.couriertsi.domain.OrderTrack;
import com.yogapay.couriertsi.domain.OrderVol;
import com.yogapay.couriertsi.domain.OrderZidan;
import com.yogapay.couriertsi.domain.RateConfig;
import com.yogapay.couriertsi.domain.RoadConfig;
import com.yogapay.couriertsi.domain.Route;
import com.yogapay.couriertsi.domain.Station;
import com.yogapay.couriertsi.domain.Substation;
import com.yogapay.couriertsi.domain.SubstationDistance;
import com.yogapay.couriertsi.domain.SubstationMerchant;
import com.yogapay.couriertsi.services.BehalfPayService;
import com.yogapay.couriertsi.services.BossUserService;
import com.yogapay.couriertsi.services.CourierPayService;
import com.yogapay.couriertsi.services.CustomService;
import com.yogapay.couriertsi.services.OrderAddrService;
import com.yogapay.couriertsi.services.OrderPayinfoService;
import com.yogapay.couriertsi.services.OrderPrepayService;
import com.yogapay.couriertsi.services.OrderRemarkService;
import com.yogapay.couriertsi.services.OrderRouteService;
import com.yogapay.couriertsi.services.OrderScanService;
import com.yogapay.couriertsi.services.OrderService;
import com.yogapay.couriertsi.services.OrderTrackService;
import com.yogapay.couriertsi.services.OrderVolService;
import com.yogapay.couriertsi.services.OrderZidanService;
import com.yogapay.couriertsi.services.RateConfigService;
import com.yogapay.couriertsi.services.RoadConfigService;
import com.yogapay.couriertsi.services.RoadFreightService;
import com.yogapay.couriertsi.services.RouteService;
import com.yogapay.couriertsi.services.StationService;
import com.yogapay.couriertsi.services.SubstationDistanceService;
import com.yogapay.couriertsi.services.SubstationMerchantService;
import com.yogapay.couriertsi.services.SubstationService;
import com.yogapay.couriertsi.utils.CommonResponse;
import com.yogapay.couriertsi.utils.DateUtils;
import com.yogapay.couriertsi.utils.JsonUtil;
import com.yogapay.couriertsi.utils.StringUtils;


@Controller
@RequestMapping(value="/order")
@Scope("prototype")
public class OrderApi extends BaseApi{
	@Resource
	private OrderService orderService ;
	@Resource
	private BossUserService bossUserService ;
	@Resource
	private OrderRemarkService orderRemarkService ;
	@Resource
	private CustomService customService ;
	@Resource
	private OrderAddrService orderAddrService ;
	@Resource private SubstationService substationService ;
	@Resource private RouteService routeService ;
	@Resource private RoadConfigService roadConfigService ;
	@Resource private SubstationDistanceService substationDistanceService ;
	@Resource private RoadFreightService roadFreightService ;
	@Resource private RateConfigService rateConfigService ;
	@Resource private OrderPrepayService orderPrepayService ;
	@Resource
	private StationService stationService ;
	@Resource
	private BehalfPayService behalfPayService ;
	@Resource
	private CourierPayService courierPayService ;
	@Resource
	private OrderVolService orderVolService ;
	@Resource
	private OrderTrackService orderTrackService ;
	@Resource
	private OrderZidanService orderZidanService ;
	@Resource private OrderScanService orderScanService ;
	@Resource
	OrderRouteService orderRouteService ;
	@Resource
	private OrderPayinfoService orderPayinfoService ;
	@Resource
	private SubstationMerchantService substationMerchantService ;
	

	@RequestMapping(value = "info")	
	public void info(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"no_val"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
        Map<String, Object> order_info = null ;
               if ("1".equals(params.get("no_type"))) {
	               order_info = orderService.getOrderInfoById(params.get("no_val")) ;
             }else {
            	    order_info = orderService.getOrderInfoByNo(params.get("no_val")) ;
			}
			if (order_info==null) {
				render(JSON_TYPE, CommonResponse.respFailJson("9005","订单不存在", params.get("reqNo")), response);
				return ;
			}	
			   OrderRoute orderRoute = orderRouteService.getByOrderNo(params.get("no_val")) ;
			   String sub_route = "" ;
			   if (orderRoute!=null) {
				  sub_route = orderRoute.getSubRoute() ;
			   }
			   model.put("sub_route", sub_route) ;
               model.put("order_info", order_info) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}

	@RequestMapping(value = "unacpt")	
	public void unacpt(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_id"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				Map<String, Object> acptMap = orderService.getAcptByUserName(userName,params.get("order_id"),"1") ;
				if (acptMap==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9003","不是当前分配无法退回", params.get("reqNo")), response);
					return ;
				}
                orderService.unacpt(params.get("order_id"));
				render(JSON_TYPE, CommonResponse.respSuccessJson("","退回成功", params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	@RequestMapping(value = "untake")	
	public void untake(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_id","order_note"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				Map<String, Object> acptMap = orderService.getAcptByUserName(userName,params.get("order_id"),"1") ;
				if (acptMap==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9003","不是当前分配无法拒收", params.get("reqNo")), response);
					return ;
				}
                orderService.untake(params.get("order_id"));
                
                OrderReamrk reamrk = new OrderReamrk() ;
                reamrk.setOrderId(Integer.valueOf(params.get("order_id")));
                reamrk.setRemark("订单拒收："+params.get("order_note"));
                reamrk.setOperate(userName);
                reamrk.setRealName(bossUser.getRealName());
                reamrk.setCreateTime(new Date());
                orderRemarkService.save(reamrk);
                
				render(JSON_TYPE, CommonResponse.respSuccessJson("","拒收成功", params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	

	
	@RequestMapping(value = "vipinfo")	
	public void vipinfo(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"vip_code"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				Map<String, Object> vip_info = customService.getByCode(params.get("vip_code")) ;
				if (vip_info==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9006","vip客户不存在", params.get("reqNo")), response);
					return ;
				}
                 model.put("vip_info", vip_info) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}

	
	
	@RequestMapping(value = "sendinfo")	
	public void sendinfo(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				if (StringUtils.isEmptyWithTrim(params.get("send_name"))&&StringUtils.isEmptyWithTrim(params.get("send_tell"))) {
					render(JSON_TYPE, CommonResponse.respFailJson("9001","缺少参数send_name或send_tell", params.get("reqNo")), response);
					return ;
				}
				List<Map<String, Object>> send_list = null ;
				if (!StringUtils.isEmptyWithTrim(params.get("send_name"))) {
					send_list = orderAddrService.getBySendName(params.get("send_name")) ;
				}else {
					send_list = orderAddrService.getBySendTell(params.get("send_tell")) ;
				}
                 model.put("send_list", send_list) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	@RequestMapping(value = "orderno")	
	public void orderno(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				String order_no = "" ;
				String curDate = DateUtils.formatDate(new Date(), "yyyyMMdd");
				String cdate = sequenceService.getCurrVal("cur_date");
				String nextLgcOrderNo = "0000001";
				if (curDate.equals(cdate)) {
					nextLgcOrderNo = sequenceService.getNextVal("cur_order_no").substring(1);
				} else {
					if ("0".equals(cdate)) {
						render(JSON_TYPE, CommonResponse.respFailJson("9007","缺少单号配置信息", params.get("reqNo")), response);
						return ;
					}
					sequenceService.setCurrVal("cur_date", curDate);
					sequenceService.setCurrVal("cur_order_no", "10000001");
				}
				 order_no = "956" + curDate.substring(4) + nextLgcOrderNo;
                 model.put("order_no", order_no) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	
	@RequestMapping(value = "sendstation")	
	public void sendstation(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"province_id","city_id","area_id"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                 List<Map<String, Object>> substation_list = substationService.getByArea(params.get("province_id"), params.get("city_id"), params.get("area_id")) ;
                 if (substation_list==null||substation_list.size()<1) {
                	 render(JSON_TYPE, CommonResponse.respFailJson("9008","该区域公司暂不进行收派件！", params.get("reqNo")), response);
						return ;
				}
                 model.put("substation_list", substation_list) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	
	@RequestMapping(value = "route")	
	public void route(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"send_sno"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
                 List<Map<String, Object>> route_list = routeService.getBySubstation(bossUser.getSno(), params.get("send_sno")) ;
                 model.put("route_list", route_list) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	@RequestMapping(value = "freight")	
	public void freight(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"route_id","freight_type","weight_charging","goods_name"}, true);
			
			if ("TRUE".equals(ret.get("isSuccess"))) {
                 
				if (!"1".equals(params.get("freight_type"))&!"2".equals(params.get("freight_type"))) {
					render(JSON_TYPE, CommonResponse.respFailJson("9001","freight_type错误", params.get("reqNo")), response);
					return ;
				}
				float weight = 0 ;
				try {
					 weight = Float.valueOf(params.get("weight_charging")) ;
				} catch (Exception e) {
					render(JSON_TYPE, CommonResponse.respFailJson("9001","weight_charging错误", params.get("reqNo")), response);
					return ;
				}
				
				boolean rate = true ;
				RateConfig rateConfig1 = rateConfigService.getByWeightType(weight, 1) ;
				RateConfig rateConfig2 = null ;
				double rate2 = 1 ;
				if (rateConfig1==null) {
					rate = false ;
				}else {
					if ("2".equals(params.get("freight_type"))) {
						rateConfig2 = rateConfigService.getByRWeightType( 2) ;
						if (rateConfig2==null) {
							rate = false ;
						}else {
							rate2 = rateConfig2.getRate() ;
						}
					}
				}
				float freight = 0 ;
				Route route = routeService.getById(params.get("route_id")) ;
				//两段公路与铁路成本
				float route_freight1 = 0 ;
				float route_freight2 = 0 ;
				float road_freight = 0 ;
				if (rate&&route!=null) {
					 RoadConfig roadConfig1 = roadConfigService.getBySub(route.getSub1(), route.getSub3()) ;
					 RoadConfig roadConfig2 = roadConfigService.getBySub(route.getSub6(), route.getSub4()) ;
					 SubstationDistance distance1 = substationDistanceService.getBySub(route.getSub1(), route.getSub3()) ;
					 SubstationDistance distance2 = substationDistanceService.getBySub(route.getSub6(), route.getSub4()) ;
					 if (roadConfig1==null||roadConfig2==null||distance1==null||distance2==null) {
						 freight = 0 ;
					  }else {
						route_freight1 = (float) roadConfigService.getCostByWeightDistance(weight, distance1.getDistance(), roadConfig1) ;
						route_freight2 = (float) roadConfigService.getCostByWeightDistance(weight, distance2.getDistance(), roadConfig2) ;
					    Station station1 =	stationService.getBySno(route.getSub3()) ;
					    Station station2 =	stationService.getBySno(route.getSub4()) ;
					    if (station1!=null&&station2!=null) {
					    	road_freight = roadFreightService.getFreight(params.get("weight_charging"), params.get("goods_name"),station1.getStationName(), station2.getStationName()) ;
						}
						if (road_freight<0.01) {
							 freight = 0 ;
						}else {
							freight = route_freight1 + route_freight2 + road_freight ;
						}
					}
				}
				int order_prepay_id = 0 ;
				int f = Math.round(freight) ;
				if (freight>0) {
					freight = (float) (freight * rateConfig1.getRate() * rate2) ;
					f = Math.round(freight) ;
					OrderPrepay orderPrepay = new OrderPrepay() ;
					orderPrepay.setCreateTime(new Date());
					orderPrepay.setFreight(Double.valueOf(f));
					orderPrepay.setFreightType(Integer.valueOf(params.get("freight_type")));
					orderPrepay.setGoodsName(params.get("goods_name"));
					orderPrepay.setRailCost(Double.valueOf(road_freight));
					orderPrepay.setRoadCostFrom(Double.valueOf(route_freight1));
					orderPrepay.setRoadCostTo(Double.valueOf(route_freight2));
					orderPrepay.setRouteId(Integer.valueOf(params.get("route_id")));
					orderPrepay.setWeightCharging(params.get("weight_charging"));
					order_prepay_id = (int) orderPrepayService.save(orderPrepay) ;
				}
				
				 model.put("order_prepay_id", order_prepay_id) ;
                 model.put("freight", f) ;
				render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	


	
	@RequestMapping(value = "take")	
	public void take(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] 
					{"order_no","send_tell","send_name","send_province_id",
					"send_city_id","send_area_id","send_street_id","send_detail","send_address",
					"rev_tell","rev_name","rev_province_id","rev_city_id","rev_area_id",
					"rev_street_id","rev_detail","rev_address","send_sno","route_id",
					"goods_name","package_type","quantity","weight_real","weight_vol",
					"weight_charging","vol_json","insured","insured_fee","behalf",
					"behalf_fee","freight","order_prepay_id",
					"server_type","freight_type","take_money"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				Substation substation = substationService.getBySno(bossUser.getSno()) ;
				OrderInfo orderInfo = new OrderInfo() ;
				OrderAddr orderAddr = new OrderAddr() ;
				Date nowDate = new Date() ;
				
				boolean add = false ;
				if (StringUtils.isEmptyWithTrim(params.get("order_id"))) {
					orderInfo.setSource("APP");
					orderInfo.setCreateTime(nowDate);
					add = true ;
				}else {
					orderInfo = orderService.getById(params.get("order_id")) ;
				}
				if (orderInfo==null) {
					render(JSON_TYPE, CommonResponse.respFailJson("9999","order_id有误", params.get("reqNo")), response);
					return ;
				}
				
				if (orderInfo.getFreightPayStatus()!=null&&orderInfo.getFreightPayStatus()==2) {
            		render(JSON_TYPE, CommonResponse.respFailJson("9999","订单支付中，请勿进行其他操作", params.get("reqNo")), response);
					return ;
				}
				
				 OrderInfo o = orderService.getByOrderNo(params.get("order_no")) ;
				 if (o!=null&&add) {
					 render(JSON_TYPE, CommonResponse.respFailJson("9999","运单号重复了，请重新获取", params.get("reqNo")), response);
						return ;
				 }
				
				 double total_vol = 0 ;
				 double total_length = 0 ;
				 double total_width = 0 ;
				 double total_height = 0 ;
				 List<OrderVol> vols = new ArrayList<OrderVol>() ;
				try {
				orderAddr.setVipCode(params.get("vip_code"));
				orderAddr.setSendTell(params.get("send_tell"));
				orderAddr.setSendName(params.get("send_name"));
				orderAddr.setSendCard(params.get("send_card"));
				orderAddr.setSendProvinceId(Integer.valueOf(params.get("send_province_id")));
				orderAddr.setSendCityId(Integer.valueOf(params.get("send_city_id")));
				orderAddr.setSendAreaId(Integer.valueOf(params.get("send_area_id")));
				orderAddr.setSendStreetId(Integer.valueOf(params.get("send_street_id")));
				orderAddr.setSendDetail(params.get("send_detail"));
				orderAddr.setSendAddress(params.get("send_address"));
				orderAddr.setRevTell(params.get("rev_tell"));
				orderAddr.setRevName(params.get("rev_name"));
				orderAddr.setRevProvinceId(Integer.valueOf(params.get("rev_province_id")));
				orderAddr.setRevCityId(Integer.valueOf(params.get("rev_city_id")));
				orderAddr.setRevAreaId(Integer.valueOf(params.get("rev_area_id")));
				orderAddr.setRevStreetId(Integer.valueOf(params.get("rev_street_id")));
				orderAddr.setRevDetail(params.get("rev_detail"));
				orderAddr.setRevAddress(params.get("rev_address"));
					
				
				vols = JsonUtil.getDTOList(params.get("vol_json"), OrderVol.class) ;
				 for (OrderVol vol:vols) {
					vol.setOrderNo(params.get("order_no"));
					total_vol += vol.getVol() ;
					total_length += vol.getLength() ;
					total_width += vol.getWidth() ;
					total_height += vol.getHeight() ;
				 }
				
				orderInfo.setOrderNo(params.get("order_no"));
				orderInfo.setSendSno(params.get("send_sno"));
				orderInfo.setGoodsName(params.get("goods_name"));
				orderInfo.setPackageType(params.get("package_type"));
				orderInfo.setQuantity(Integer.valueOf(params.get("quantity")));
				orderInfo.setWeightReal(Double.valueOf(params.get("weight_real")));
				orderInfo.setWeightVol(Double.valueOf(params.get("weight_vol")));
				orderInfo.setWeightCharging(Double.valueOf(params.get("weight_charging")));
				orderInfo.setInsured(Double.valueOf(params.get("insured")));
				orderInfo.setInsuredFee(Double.valueOf(params.get("insured_fee")));
				orderInfo.setBehalf(Double.valueOf(params.get("behalf")));
				orderInfo.setBehalfFee(Double.valueOf(params.get("behalf_fee")));
				orderInfo.setReceiptType(params.get("receipt_type"));
				orderInfo.setReceiptNo(params.get("receipt_no"));
				orderInfo.setZidanNo(params.get("zidan_no"));
				orderInfo.setFreight(Double.valueOf(params.get("freight")));
				orderInfo.setServerType(Integer.valueOf(params.get("server_type")));
				orderInfo.setFreightType(Integer.valueOf(params.get("freight_type")));
				orderInfo.setFreightPayType(Integer.valueOf(params.get("freight_pay_type")));
				orderInfo.setTakeMoney(Double.valueOf(params.get("take_money")));
				orderInfo.setNote(params.get("note"));
				} catch (Exception e) {
					e.printStackTrace();
					render(JSON_TYPE, CommonResponse.respFailJson("9999","数据有误", params.get("reqNo")), response);
					return ;
				}
				String v = orderInfo.validate() ;
				if (!"1".equals(v)) {
					render(JSON_TYPE, CommonResponse.respFailJson("9999",v, params.get("reqNo")), response);
					return ;
				}
				
				Map<String, Object>	vipMap = null ;
				boolean vip = false ;
				if (!StringUtils.isEmptyWithTrim(params.get("vip_code"))) {
					vipMap = customService.getByCode(params.get("vip_code")) ;
					if (vipMap==null) {
						 render(JSON_TYPE, CommonResponse.respFailJson("9999","vip客户不存在", params.get("reqNo")), response);
							return ;
					}
					vip = true ;
				}
				                 
				
				 Route route = routeService.getById(params.get("route_id")) ;
				 if (route==null) {
					 render(JSON_TYPE, CommonResponse.respFailJson("9999","route_id有误", params.get("reqNo")), response);
						return ;
				 }
                 List<Map<String, Object>> route_list = routeService.getBySubstation(bossUser.getSno(), params.get("send_sno")) ;
                 boolean route_flag = false ;
                  for (Map<String, Object> map:route_list) {
					 if (route.getId()==Integer.valueOf(map.get("route_id").toString())) {
						route_flag = true ;
						break ;
					}
				  }
                 if (!route_flag) {
                	 render(JSON_TYPE, CommonResponse.respFailJson("9999","路线有误请重新获取选择", params.get("reqNo")), response);
						return ;
				 }
                 
                 OrderRoute orderRoute = new OrderRoute() ;
                 orderRoute.setOrderNo(params.get("order_no"));
                 orderRoute.setSub1(route.getSub1());
                 orderRoute.setSub2(route.getSub2());
                 orderRoute.setSub3(route.getSub3());
                 orderRoute.setSub4(route.getSub4());
                 orderRoute.setSub5(route.getSub5());
                 orderRoute.setSub6(route.getSub6());
                 orderRoute.setSubRoute(route.getRouteText());
                 
                 OrderPrepay prepay =  orderPrepayService.getById(Integer.valueOf(params.get("order_prepay_id"))) ;
                 if (prepay==null) {
                	    render(JSON_TYPE, CommonResponse.respFailJson("9999","order_prepay_id错误，请重新计算运费", params.get("reqNo")), response);
						return ;
				 }
                 if (prepay.getFreightType()!=orderInfo.getFreightType()||!prepay.getGoodsName().equals(orderInfo.getGoodsName())||
                		 prepay.getRouteId()!=route.getId()||Math.abs(Double.valueOf(prepay.getWeightCharging())-orderInfo.getWeightCharging())>0.01) {
                	 System.err.println(prepay.getFreightType()!=orderInfo.getFreightType());
                	 System.err.println(!prepay.getGoodsName().equals(orderInfo.getGoodsName()));
                	 System.err.println(prepay.getRouteId()!=route.getId());
                	 System.err.println(Double.valueOf(prepay.getWeightCharging())!=orderInfo.getWeightCharging());
                	    render(JSON_TYPE, CommonResponse.respFailJson("9999","订单已修改，请重新计算运费", params.get("reqNo")), response);
						return ;
				}
                 
                 boolean cod = false ;
                 BehalfPay behalfPay = new BehalfPay() ;
                 if (orderInfo.getBehalf()>0) {
					cod = true ;
					if (StringUtils.isEmptyWithTrim(params.get("bank"))||StringUtils.isEmptyWithTrim(params.get("bank_code"))||
							StringUtils.isEmptyWithTrim(params.get("bank_user_name"))||StringUtils.isEmptyWithTrim(params.get("bank_acct"))) {
						render(JSON_TYPE, CommonResponse.respFailJson("9999","代收款信息不完整", params.get("reqNo")), response);
						return ;
					}
					behalfPay.setBank(params.get("bank"));
					behalfPay.setBankCode(params.get("bank_code"));
					behalfPay.setBankUserName(params.get("bank_user_name"));
					behalfPay.setBankAcct(params.get("bank_acct"));
					behalfPay.setOrderNo(params.get("order_no"));
					behalfPay.setMoney(orderInfo.getBehalf()-orderInfo.getBehalfFee());
					behalfPay.setIsPay(0);
					
					orderInfo.setBehalfPayStatus(0);
				}else {
					orderInfo.setBehalfPayStatus(1);
				}
                
                 if (Math.abs(prepay.getFreight()-orderInfo.getFreight())>0.01) {
                	 render(JSON_TYPE, CommonResponse.respFailJson("9999","邮费不正确", params.get("reqNo")), response);
						return ;
				}
                
                 double takeMoney = prepay.getFreight() ;
                 double send_money = orderInfo.getBehalf() ;
                 double freight_real =  prepay.getFreight() ;
                 if (vip) {
                	 String d = vipMap.get("discount")==null?"0.0":vipMap.get("discount").toString() ;
					 takeMoney = takeMoney * Double.valueOf(d)/100.0 ;
					 freight_real = freight_real * Double.valueOf(d)/100.0 ;
				 }
                 takeMoney = takeMoney + orderInfo.getInsuredFee() ;
                 takeMoney = Math.round(takeMoney) ;   //四舍五入
                 double tatalFreight = takeMoney ;
                 
                 if (orderInfo.getFreightType()==2) {
					send_money += takeMoney ;
					takeMoney = 0 ;
				 }else {
					orderInfo.setFreightPayStatus(1);
				}
                if (Math.abs(takeMoney-orderInfo.getTakeMoney())>0.01) {
                	render(JSON_TYPE, CommonResponse.respFailJson("9999","应收金额不正确", params.get("reqNo")), response);
					return ;
				 }
                orderInfo.setStatus(2);
                
                CourierPay courierPay = new CourierPay() ;
                if (takeMoney>0) {
                	courierPay.setCourierNo(bossUser.getUserName()); 
                	courierPay.setCreateTime(nowDate);
                	courierPay.setRevMoney(takeMoney);
                	courierPay.setPayMoney(takeMoney);
                	courierPay.setType(1);
                	//courierPay.setFreight(orderInfo.getFreight());
                	courierPay.setFreight(takeMoney);
                	courierPay.setInsuredFee(orderInfo.getInsuredFee());
                	courierPay.setBehalf(0.0);
                	
                	if (orderInfo.getFreightPayType()==1) {
    					//现金
                    	orderInfo.setFreightSettleStatus(0);
                    	
                    	courierPay.setIsPay(0);
                    	
    				}else {
    					//刷卡
    					orderInfo.setFreightSettleStatus(0);
    					//orderInfo.setFreightSettleTime(nowDate);
    					orderInfo.setFreightPayStatus(2);
    					orderInfo.setStatus(1);
    					
    					courierPay.setIsPay(1);
    					
    					/*render(JSON_TYPE, CommonResponse.respFailJson("9999","暂不支持刷卡支付", params.get("reqNo")), response);
    					return ;*/
    				}
				}
                
                orderInfo.setSendMoney(send_money);
                
                orderInfo.setTotalVol(Double.valueOf(total_vol));
                orderInfo.setLength(total_length);
                orderInfo.setWidth(total_width);
                orderInfo.setHeight(total_height);
                orderInfo.setTakeSno(bossUser.getSno());
                orderInfo.setTakeTime(nowDate);
                orderInfo.setTakeCourierNo(bossUser.getUserName());
                orderInfo.setFreightReal(freight_real);
                orderInfo.setRoadCostFrom(prepay.getRoadCostFrom());
                orderInfo.setRoadCostTo(prepay.getRoadCostTo());
                orderInfo.setRailCost(prepay.getRailCost());
                orderInfo.setTotalFee(tatalFreight+orderInfo.getBehalf());
                
                
                int oid = 0 ;
                if (add) {
					oid = (int) orderService.save(orderInfo) ;
				}else {
					orderService.takeUpdate(orderInfo);
					oid = orderInfo.getId() ;
				}
                orderAddr.setOrderId(oid);
                courierPay.setOrderId(oid);
                orderAddrService.save(orderAddr) ;
                if (cod) {
					behalfPayService.save(behalfPay) ;
				}
                orderRouteService.save(orderRoute) ; 
                if (takeMoney>0) {
                	courierPayService.save(courierPay,true) ;	
				}
                orderVolService.delByOrderNo(params.get("order_no"));
                for (OrderVol vol:vols) {
                	orderVolService.save(vol) ;
				}
                
                String zidan = params.get("zidan_no")==null?"":params.get("zidan_no") ;
                String []zd = zidan.split(",") ;
                for (String z:zd) {
					if (!StringUtils.isEmptyWithTrim(z)) {
						OrderZidan orderZidan = new OrderZidan() ;
						orderZidan.setOrderNo(params.get("order_no"));
						orderZidan.setZidanNo(z);
						orderZidanService.save(orderZidan,true) ;
					}
				}
                
                OrderTrack orderTrack = new OrderTrack() ;
				orderTrack.setOrderNo(params.get("order_no"));
				orderTrack.setContext("【"+substation.getSubstationName()+"】已经收取快件，收件员："+bossUser.getRealName()+"，电话："+bossUser.getPhone());
				orderTrack.setCompleted(0);
				orderTrack.setCreateTime(nowDate);
				orderTrack.setCurrentSubno(bossUser.getSno());
				int tid = (int) orderTrackService.save(orderTrack,true) ;
				
				
				OrderScan orderScan = new OrderScan() ;
                orderScan.setCarMark(null);
                orderScan.setCreateTime(nowDate);
                orderScan.setOpName(bossUser.getRealName());
                orderScan.setOpSource(1);
                orderScan.setOpType("take");
                orderScan.setOrderNo(params.get("order_no"));
                orderScan.setSub(bossUser.getSno());
                orderScanService.save(orderScan,true) ;
				
                model.put("order_id", oid) ;
                 
				render(JSON_TYPE, CommonResponse.respSuccessJson("收件成功",model, params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	
	@RequestMapping(value = "payinfo")	
	public void payinfo(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_id","type","amount","longitude","latitude"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
				BossUser bossUser = bossUserService.getByUserName(userName) ;
				
				Map<String, Object> payinfo = new HashMap<String, Object>() ;
                Date nowDate = new Date() ;
				int type =1 ;
				if ("2".equals(params.get("type"))) {
					type = 2 ;
				}

				   OrderInfo orderInfo = orderService.getById(params.get("order_id")) ;
				   if (orderInfo==null) {
                	   render(JSON_TYPE, CommonResponse.respFailJson("9001","order_id错误", params.get("reqNo")), response);
					    return ;
					}
				   
				 /*  if (orderInfo.getFreightPayStatus()==2) {
               		render(JSON_TYPE, CommonResponse.respFailJson("9999","订单支付中，请勿进行其他操作", params.get("reqNo")), response);
   					return ;
					}
*/
				   OrderRoute orderRoute = orderRouteService.getByOrderNo(orderInfo.getOrderNo()) ;
				   if (orderRoute==null) {
                	   render(JSON_TYPE, CommonResponse.respFailJson("9001","order_id错误", params.get("reqNo")), response);
					    return ;
					}
				 
				   
				   Integer amount = Integer.valueOf(params.get("amount")) ;
				   double fee =  amount*6/100000.0 ;   
				   
                if (type==1) {
					
                	 if (orderInfo.getStatus()!=1||orderInfo.getFreightPayStatus()==1) {
                		 render(JSON_TYPE, CommonResponse.respFailJson("9999","订单错误", params.get("reqNo")), response);
 					    return ;
 				     }
                	 
                   String merchantId = "" ;
                   String merchantId1 = "" ;
                   String merchantId2 = "" ;
                   String merchantId5 = "" ;
                   String merchantId6 = "" ;
                   
                	Substation substation = substationService.getByLevel(1) ;
                   if (substation==null) {
                   	   render(JSON_TYPE, CommonResponse.respFailJson("9001","总部网点未设定", params.get("reqNo")), response);
   					    return ;
					}
                   SubstationMerchant substationMerchant = substationMerchantService.getBySubno(substation.getSubstationNo()) ;
                   if (substationMerchant==null) {
                	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，总部网点未绑定商户号", params.get("reqNo")), response);
  					    return ;
				   }
                   merchantId = substationMerchant.getMerchantNo() ;
                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub1()) ;
                   if (substationMerchant==null) {
               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
 					    return ;
				   }
                   merchantId1 = substationMerchant.getMerchantNo() ;
                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub2()) ;
                   if (substationMerchant==null) {
               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
 					    return ;
				   }
                   merchantId2 = substationMerchant.getMerchantNo() ;
                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub5()) ;
                   if (substationMerchant==null) {
               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
 					    return ;
				   }
                   merchantId5 = substationMerchant.getMerchantNo() ;
                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub6()) ;
                   if (substationMerchant==null) {
               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
 					    return ;
				   }
                   merchantId6 = substationMerchant.getMerchantNo() ;
               
                   
                  double s = orderInfo.getFreight()-orderInfo.getRoadCostTo()-orderInfo.getRoadCostFrom()-orderInfo.getRailCost()-fee ;
                  if (s<0.01) {
                	  render(JSON_TYPE, CommonResponse.respFailJson("9999","未知错误", params.get("reqNo")), response);
					    return ;
				  }
                  
                  double sub1_profit = s*0.3 ;
                  double sub2_profit = s*0.2 ;
                  double sub5_profit = s*0.1 ;
                  double sub6_profit = s*0.2 ;
                  double sub_normal_profit = s-sub1_profit-sub2_profit-sub5_profit-sub6_profit ;
                  orderRoute.setSubNormalProfit(sub_normal_profit);
                  orderRoute.setSub1Profit(sub1_profit);
                  orderRoute.setSub2Profit(sub2_profit);
                  orderRoute.setSub5Profit(sub5_profit);
                  orderRoute.setSub6Profit(sub6_profit);
                  
                  List<Map<String, Object>> distMerchant = new ArrayList<Map<String,Object>>() ;
                  Map<String, Object> map1 = new HashMap<String, Object>() ;
                  map1.put("merchantId", merchantId1) ;
                  map1.put("amount", (int)Math.round(sub1_profit*100)) ;
                  distMerchant.add(map1) ;
                  
                  Map<String, Object> map2 = new HashMap<String, Object>() ;
                  map2.put("merchantId", merchantId2) ;
                  map2.put("amount", (int)Math.round(sub2_profit*100)) ;
                  distMerchant.add(map2) ;
                  
                  Map<String, Object> map5 = new HashMap<String, Object>() ;
                  map5.put("merchantId", merchantId5) ;
                  map5.put("amount", (int)Math.round(sub5_profit*100)) ;
                  distMerchant.add(map5) ;
                  
                  Map<String, Object> map6 = new HashMap<String, Object>() ;
                  map6.put("merchantId", merchantId6) ;
                  map6.put("amount", (int)Math.round(sub6_profit*100)) ;
                  distMerchant.add(map6) ;
                  
                   
                  String payNo = sequenceService.getNextVal("pay_no") ;
                  OrderPayinfo orderPayinfo = new OrderPayinfo() ;
                  orderPayinfo.setOrderId(orderInfo.getId());
                  orderPayinfo.setAmount(Integer.valueOf(params.get("amount")));
                  orderPayinfo.setCreateTime(nowDate);
                  orderPayinfo.setType(1);
                  orderPayinfo.setOrgId(configInfo.getOrg_id());
                  int f = (int) Math.round(fee*100.0) ;
                  orderPayinfo.setFee(f);
                  orderPayinfo.setPayNo(payNo);
                  String p = "slapi";
                  orderPayinfo.setParams(p);
                   com.yogapay.couriertsi.pay.OrderInfo o = new com.yogapay.couriertsi.pay.OrderInfo() ;
                   o.setAmount(String.valueOf(amount));
          		   o.setOrderId(String.valueOf(payNo));
          		   o.setParams(p);
          		   Map<String, Object> msgMap = new HashMap<String, Object>() ;
          		   msgMap.put("amount", o.getAmount());
          		   msgMap.put("params", o.getParams());
          		   msgMap.put("orderId", o.getOrderId());
          		   msgMap.put("latitude", params.get("latitude"));
          		   msgMap.put("longitude", params.get("longitude"));
          		   String orderMsg = res(JsonUtil.toJson(msgMap), configInfo.getApp_public_key()) ;
          		   String goodsName = "运费" ;
                   String resp ="orderId="+payNo+"&amount="+amount+"&orgId="+configInfo.getOrg_id()+"&distMerchant="+JsonUtil.toJson(distMerchant)
                   		+ "&merchantId="+merchantId+"&goodsDesc="+goodsName+"&merchantName=善良&longitude="+params.get("longitude")+"&latitude="+params.get("latitude")+"&merchantMessage="+orderMsg;   
                   orderPayinfo.setResp(resp);
                   
                   orderRouteService.upFreightProfit(orderRoute);
                   orderPayinfoService.save(orderPayinfo) ;
                   
                   payinfo.put("pay_no", payNo) ;
                   payinfo.put("pay_param", resp) ;
                   	
				}else {
					
					double snpay  = orderInfo.getSendMoney() ;
					if (snpay<0.01) {
						 render(JSON_TYPE, CommonResponse.respFailJson("9999","当前订单无需付款", params.get("reqNo")), response);
						 return ;
					}
					   if (!bossUser.getSno().equals(orderInfo.getSendSno())) {
		                	render(JSON_TYPE, CommonResponse.respFailJson("9999","当前站点和目的地网点不符", params.get("reqNo")), response);
							return ;
						}
					
					    String merchantId = "" ;
						Substation substation = substationService.getByLevel(1) ;
		                   if (substation==null) {
		                   	   render(JSON_TYPE, CommonResponse.respFailJson("9001","总部网点未设定", params.get("reqNo")), response);
		   					    return ;
							}
		                   SubstationMerchant substationMerchant = substationMerchantService.getBySubno(substation.getSubstationNo()) ;
		                   if (substationMerchant==null) {
		                	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，总部网点未绑定商户号", params.get("reqNo")), response);
		  					    return ;
						   }
		                   merchantId = substationMerchant.getMerchantNo() ;
		                   List<Map<String, Object>> distMerchant = new ArrayList<Map<String,Object>>() ;
		                   String goodsName = "代收货款" ;     
					if (orderInfo.getFreightType()==2) {
						
						   goodsName = "运费-代收货款" ;     
		                   String merchantId1 = "" ;
		                   String merchantId2 = "" ;
		                   String merchantId5 = "" ;
		                   String merchantId6 = "" ;
		                   
		                
		                 
		                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub1()) ;
		                   if (substationMerchant==null) {
		               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
		 					    return ;
						   }
		                   merchantId1 = substationMerchant.getMerchantNo() ;
		                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub2()) ;
		                   if (substationMerchant==null) {
		               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
		 					    return ;
						   }
		                   merchantId2 = substationMerchant.getMerchantNo() ;
		                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub5()) ;
		                   if (substationMerchant==null) {
		               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
		 					    return ;
						   }
		                   merchantId5 = substationMerchant.getMerchantNo() ;
		                   substationMerchant = substationMerchantService.getBySubno(orderRoute.getSub6()) ;
		                   if (substationMerchant==null) {
		               	    render(JSON_TYPE, CommonResponse.respFailJson("9001","无法刷卡支付，路线网点未绑定商户号", params.get("reqNo")), response);
		 					    return ;
						   }
		                   merchantId6 = substationMerchant.getMerchantNo() ;
		                   
		                 
		                  double s = orderInfo.getFreight()-orderInfo.getRoadCostTo()-orderInfo.getRoadCostFrom()-orderInfo.getRailCost() ;
		                   if (orderInfo.getBehalf()<0.01) {
							  s-=fee ;
							  goodsName = "运费" ;     
						   }
		                  if (s<0.01) {
		                	  render(JSON_TYPE, CommonResponse.respFailJson("9999","未知错误", params.get("reqNo")), response);
							    return ;
						  }
		                  
		                  double sub1_profit = s*0.3 ;
		                  double sub2_profit = s*0.2 ;
		                  double sub5_profit = s*0.1 ;
		                  double sub6_profit = s*0.2 ;
		                  double sub_normal_profit = s-sub1_profit-sub2_profit-sub5_profit-sub6_profit ;
		                  orderRoute.setSubNormalProfit(sub_normal_profit);
		                  orderRoute.setSub1Profit(sub1_profit);
		                  orderRoute.setSub2Profit(sub2_profit);
		                  orderRoute.setSub5Profit(sub5_profit);
		                  orderRoute.setSub6Profit(sub6_profit);
		                  
		                  
		                  Map<String, Object> map1 = new HashMap<String, Object>() ;
		                  map1.put("merchantId", merchantId1) ;
		                  map1.put("amount", (int)Math.round(sub1_profit*100)) ;
		                  distMerchant.add(map1) ;
		                  
		                  Map<String, Object> map2 = new HashMap<String, Object>() ;
		                  map2.put("merchantId", merchantId2) ;
		                  map2.put("amount", (int)Math.round(sub2_profit*100)) ;
		                  distMerchant.add(map2) ;
		                  
		                  Map<String, Object> map5 = new HashMap<String, Object>() ;
		                  map5.put("merchantId", merchantId5) ;
		                  map5.put("amount", (int)Math.round(sub5_profit*100)) ;
		                  distMerchant.add(map5) ;
		                  
		                  Map<String, Object> map6 = new HashMap<String, Object>() ;
		                  map6.put("merchantId", merchantId6) ;
		                  map6.put("amount", (int)Math.round(sub6_profit*100)) ;
		                  distMerchant.add(map6) ;
		                  
					}
					
					
					  String payNo = sequenceService.getNextVal("pay_no") ;
	                  OrderPayinfo orderPayinfo = new OrderPayinfo() ;
	                  orderPayinfo.setOrderId(orderInfo.getId());
	                  orderPayinfo.setAmount(Integer.valueOf(params.get("amount")));
	                  orderPayinfo.setCreateTime(nowDate);
	                  orderPayinfo.setType(2);
	                  orderPayinfo.setOrgId(configInfo.getOrg_id());
	                  int f = (int) Math.round(fee*100.0) ;
	                  orderPayinfo.setFee(f);
	                  orderPayinfo.setPayNo(payNo);
	                  String p = "slapi";
	                  orderPayinfo.setParams(p);
	                   com.yogapay.couriertsi.pay.OrderInfo o = new com.yogapay.couriertsi.pay.OrderInfo() ;
	                   o.setAmount(String.valueOf(amount));
	          		   o.setOrderId(String.valueOf(payNo));
	          		   o.setParams(p);
	          		   Map<String, Object> msgMap = new HashMap<String, Object>() ;
	          		   msgMap.put("amount", o.getAmount());
	          		   msgMap.put("params", o.getParams());
	          		   msgMap.put("orderId", o.getOrderId());
	          		   msgMap.put("latitude", params.get("latitude"));
	          		   msgMap.put("longitude", params.get("longitude"));
	          		   String orderMsg = res(JsonUtil.toJson(msgMap), configInfo.getApp_public_key()) ;
	          		  
	                   String resp ="orderId="+payNo+"&amount="+amount+"&orgId="+configInfo.getOrg_id()+"&distMerchant="+JsonUtil.toJson(distMerchant)
	                		   + "&merchantId="+merchantId+"&goodsDesc="+goodsName+"&merchantName=善良&longitude="+params.get("longitude")+"&latitude="+params.get("latitude")+"&merchantMessage="+orderMsg;
	                   orderPayinfo.setResp(resp);
	                   
	                    if(orderInfo.getFreightType()==2) {
	                	   orderRouteService.upFreightProfit(orderRoute);
					   }
	                  
	                   orderPayinfoService.save(orderPayinfo) ;
	                   payinfo.put("pay_no", payNo) ;
	                   payinfo.put("pay_param", resp) ;
				}
                orderService.updateFee(params.get("order_id"),fee,type) ;
                model.put("payinfo", payinfo) ;
                render(JSON_TYPE, CommonResponse.respSuccessJson("",model, params.get("reqNo")), response);  		
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}
	
	
	@RequestMapping(value = "nonepay")	
	public void nonepay(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "page_no", defaultValue = "1") int cpage){
		try {
			Map<String, String> ret = validateRequest(request,new String[] {"order_id","pay_no"}, true);
			if ("TRUE".equals(ret.get("isSuccess"))) {
				String userName = ret.get("userNo") ;
                
				  OrderInfo orderInfo = orderService.getById(params.get("order_id")) ;
				   if (orderInfo==null) {
               	   render(JSON_TYPE, CommonResponse.respFailJson("9001","order_id错误", params.get("reqNo")), response);
					    return ;
					}
				
				OrderPayinfo orderPayinfo = orderPayinfoService.getByPayNo(params.get("pay_no")) ;
				if (orderPayinfo==null) {
					 render(JSON_TYPE, CommonResponse.respFailJson("9999","pay_no错误", params.get("reqNo")), response);
 					  return ;
				}
				if (StringUtils.isNotEmptyWithTrim(orderPayinfo.getNoticeNo())) {
					  render(JSON_TYPE, CommonResponse.respFailJson("9999","无法撤销", params.get("reqNo")), response);
					  return ;
				}
				orderService.nonePayNo(params.get("order_id"));
				
				render(JSON_TYPE, CommonResponse.respSuccessJson("","撤销成功", params.get("reqNo")), response);   			
			}else {
				log.info("validate false!!!!");
				render(JSON_TYPE, CommonResponse.respFailJson(ret.get("respCode"), ret.get("respMsg"), params.get("reqNo")),response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			render(JSON_TYPE, CommonResponse.respFailJson("9000","服务器异常", params.get("reqNo")), response);
		}
	}

	
	
	
}





