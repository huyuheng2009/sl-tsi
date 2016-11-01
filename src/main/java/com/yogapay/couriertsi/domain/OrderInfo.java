package com.yogapay.couriertsi.domain;

import java.util.Date;

public class OrderInfo {
    private Integer id;

    private String orderNo;

    private String zidanNo;

    private String receiptNo;

    private Integer status;

    private String goodsName;

    private Double weightReal;

    private Integer quantity;

    private Double totalVol;

    private Date createTime;

    private String takeSno;

    private Date takeTime;

    private String takeCourierNo;

    private Integer freightType;

    private Integer assignStatus;

    private String assigner;

    private Integer serverType;

    private String packageType;

    private Double weightCharging;

    private String paySerno;

    private Date sendTime;

    private String sendSno;

    private String sendCourierNo;

    private Date signTime;

    private String signer;

    private String signImg;

    private String note;

    private Integer isPack;

    private String receiptType;

    private Double weightVol;

    private String source;

    private double length;

    private double width;

    private double height;

    private Integer isProblem;

    private String goodsImg;

    private Integer isInvalid;

    private String invalidReason;

    private Double freight;

    private Double freightReal;

    private Double behalf;

    private Double behalfFee;

    private Double insured;

    private Double insuredFee;

    private Double roadCostFrom;

    private Double roadCostTo;

    private Double railCost;

    private Double payCostFrom;

    private Double payCostTo;

    private Double totalFee;

    private Double takeMoney;

    private Double sendMoney;

    private Integer freightPayStatus;

    private Integer behalfPayStatus;

    private Integer freightPayType;

    private Integer behalfPayType;

    private Integer freightSettleStatus;

    private Integer behalfSettleStatus;

    private Date freightSettleTime;

    private Date behalfSettleTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getZidanNo() {
        return zidanNo;
    }

    public void setZidanNo(String zidanNo) {
        this.zidanNo = zidanNo == null ? null : zidanNo.trim();
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo == null ? null : receiptNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Double getWeightReal() {
        return weightReal;
    }

    public void setWeightReal(Double weightReal) {
        this.weightReal = weightReal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(Double totalVol) {
        this.totalVol = totalVol;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTakeSno() {
        return takeSno;
    }

    public void setTakeSno(String takeSno) {
        this.takeSno = takeSno == null ? null : takeSno.trim();
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public String getTakeCourierNo() {
        return takeCourierNo;
    }

    public void setTakeCourierNo(String takeCourierNo) {
        this.takeCourierNo = takeCourierNo == null ? null : takeCourierNo.trim();
    }

    public Integer getFreightType() {
        return freightType;
    }

    public void setFreightType(Integer freightType) {
        this.freightType = freightType;
    }

    public Integer getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(Integer assignStatus) {
        this.assignStatus = assignStatus;
    }

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner == null ? null : assigner.trim();
    }

    public Integer getServerType() {
        return serverType;
    }

    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
    }

    public Double getWeightCharging() {
        return weightCharging;
    }

    public void setWeightCharging(Double weightCharging) {
        this.weightCharging = weightCharging;
    }

    public String getPaySerno() {
        return paySerno;
    }

    public void setPaySerno(String paySerno) {
        this.paySerno = paySerno == null ? null : paySerno.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendSno() {
        return sendSno;
    }

    public void setSendSno(String sendSno) {
        this.sendSno = sendSno == null ? null : sendSno.trim();
    }

    public String getSendCourierNo() {
        return sendCourierNo;
    }

    public void setSendCourierNo(String sendCourierNo) {
        this.sendCourierNo = sendCourierNo == null ? null : sendCourierNo.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer == null ? null : signer.trim();
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg == null ? null : signImg.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getIsPack() {
        return isPack;
    }

    public void setIsPack(Integer isPack) {
        this.isPack = isPack;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType == null ? null : receiptType.trim();
    }

    public Double getWeightVol() {
        return weightVol;
    }

    public void setWeightVol(Double weightVol) {
        this.weightVol = weightVol;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Integer getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(Integer isProblem) {
        this.isProblem = isProblem;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    public Integer getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(Integer isInvalid) {
        this.isInvalid = isInvalid;
    }

    public String getInvalidReason() {
        return invalidReason;
    }

    public void setInvalidReason(String invalidReason) {
        this.invalidReason = invalidReason == null ? null : invalidReason.trim();
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getFreightReal() {
        return freightReal;
    }

    public void setFreightReal(Double freightReal) {
        this.freightReal = freightReal;
    }

    public Double getBehalf() {
        return behalf;
    }

    public void setBehalf(Double behalf) {
        this.behalf = behalf;
    }

    public Double getBehalfFee() {
        return behalfFee;
    }

    public void setBehalfFee(Double behalfFee) {
        this.behalfFee = behalfFee;
    }

    public Double getInsured() {
        return insured;
    }

    public void setInsured(Double insured) {
        this.insured = insured;
    }

    public Double getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(Double insuredFee) {
        this.insuredFee = insuredFee;
    }

    public Double getRoadCostFrom() {
        return roadCostFrom;
    }

    public void setRoadCostFrom(Double roadCostFrom) {
        this.roadCostFrom = roadCostFrom;
    }

    public Double getRoadCostTo() {
        return roadCostTo;
    }

    public void setRoadCostTo(Double roadCostTo) {
        this.roadCostTo = roadCostTo;
    }

    public Double getRailCost() {
        return railCost;
    }

    public void setRailCost(Double railCost) {
        this.railCost = railCost;
    }

    public Double getPayCostFrom() {
        return payCostFrom;
    }

    public void setPayCostFrom(Double payCostFrom) {
        this.payCostFrom = payCostFrom;
    }

    public Double getPayCostTo() {
        return payCostTo;
    }

    public void setPayCostTo(Double payCostTo) {
        this.payCostTo = payCostTo;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getTakeMoney() {
        return takeMoney;
    }

    public void setTakeMoney(Double takeMoney) {
        this.takeMoney = takeMoney;
    }

    public Double getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(Double sendMoney) {
        this.sendMoney = sendMoney;
    }

    public Integer getFreightPayStatus() {
        return freightPayStatus;
    }

    public void setFreightPayStatus(Integer freightPayStatus) {
        this.freightPayStatus = freightPayStatus;
    }

    public Integer getBehalfPayStatus() {
        return behalfPayStatus;
    }

    public void setBehalfPayStatus(Integer behalfPayStatus) {
        this.behalfPayStatus = behalfPayStatus;
    }

    public Integer getFreightPayType() {
        return freightPayType;
    }

    public void setFreightPayType(Integer freightPayType) {
        this.freightPayType = freightPayType;
    }

    public Integer getBehalfPayType() {
        return behalfPayType;
    }

    public void setBehalfPayType(Integer behalfPayType) {
        this.behalfPayType = behalfPayType;
    }

    public Integer getFreightSettleStatus() {
        return freightSettleStatus;
    }

    public void setFreightSettleStatus(Integer freightSettleStatus) {
        this.freightSettleStatus = freightSettleStatus;
    }

    public Integer getBehalfSettleStatus() {
        return behalfSettleStatus;
    }

    public void setBehalfSettleStatus(Integer behalfSettleStatus) {
        this.behalfSettleStatus = behalfSettleStatus;
    }

    public Date getFreightSettleTime() {
        return freightSettleTime;
    }

    public void setFreightSettleTime(Date freightSettleTime) {
        this.freightSettleTime = freightSettleTime;
    }

    public Date getBehalfSettleTime() {
        return behalfSettleTime;
    }

    public void setBehalfSettleTime(Date behalfSettleTime) {
        this.behalfSettleTime = behalfSettleTime;
    }
    
    public String validate(){
    	
    	if(this.serverType!=1&&this.serverType!=2){
    		return "serverType参数错误" ;
    	}
       if(this.freightType!=1&&this.freightType!=2){
		return "freightType参数错误" ;
	   }
       if (this.freightType==1) {
    	   if(this.freightPayType!=1&&this.freightPayType!=2){
    	   		return "freightPayType参数错误" ;
    	   	   }
	   }else {
		 this.freightPayType = null ;
	   }
       
    	return "1" ;
    }
    
    
}