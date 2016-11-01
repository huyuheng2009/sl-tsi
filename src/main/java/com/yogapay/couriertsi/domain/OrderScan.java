package com.yogapay.couriertsi.domain;

import java.util.Date;

public class OrderScan {
    private Integer id;

    private String carMark;

    private String orderNo;

    private String sub;

    private String opType;

    private Integer opSource;

    private String opName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark == null ? null : carMark.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub == null ? null : sub.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public Integer getOpSource() {
        return opSource;
    }

    public void setOpSource(Integer opSource) {
        this.opSource = opSource;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}