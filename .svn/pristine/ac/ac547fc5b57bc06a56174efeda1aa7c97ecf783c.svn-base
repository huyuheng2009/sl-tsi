package com.yogapay.couriertsi.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bsh.This;

import com.yogapay.couriertsi.push.BaiduPushAndroid;
import com.yogapay.couriertsi.push.BaiduPushIos;


public class PushMsg {

	private Integer id ;
	
	private String userNo ;
	
	private String msgCode ;
	
	private String msgTitle ;
	
	private String msgContent ;
	
	private String msgData ;
	
	private Integer readed ;
	
	private Date createTime ;
	
	private Date expireTime ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	public Integer getReaded() {
		return readed;
	}

	public void setReaded(Integer readed) {
		this.readed = readed;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	
	public BaiduPushAndroid convertAndroid() {
		 BaiduPushAndroid android = new BaiduPushAndroid() ;
   	 Map<String, Object> cusMap = new HashMap<String, Object>() ;
   	 android.setTitle(this.msgTitle);
   	 android.setDescription(this.getMsgContent());
   	 android.setOpen_type("2");
   	 cusMap.put("msgId", this.id) ;
   	 cusMap.put("msgCode", this.msgCode) ;
   	 cusMap.put("msgContent", this.msgContent) ;
   	 cusMap.put("msgData", this.msgData) ;
   	 android.setCustom_content(cusMap);
   	 
   	 return android ;  
	}
	
	public BaiduPushIos convertIos() {
	    BaiduPushIos ios = new BaiduPushIos() ;
  	    Map<String, Object> aps = new HashMap<String, Object>() ;
  	    aps.put("alert",this.msgTitle) ;
  	    aps.put("sound", "default") ;
  	    aps.put("badge", "0") ;
  	    ios.setAps(aps);
  	    ios.setMsgId(this.id);
  	    ios.setMsgCode(Integer.valueOf(this.msgCode));
       ios.setMsgData(this.getMsgData());
       return ios ;
	}
	
}
