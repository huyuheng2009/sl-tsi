package com.yogapay.couriertsi.domain;

import java.util.Date;

public class OrderPrepay {
    private Integer id;

    private Double freight;

    private Double roadCostFrom;

    private Double roadCostTo;

    private Double railCost;

    private Integer routeId;

    private Integer freightType;

    private String weightCharging;

    private String goodsName;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getFreightType() {
        return freightType;
    }

    public void setFreightType(Integer freightType) {
        this.freightType = freightType;
    }

    public String getWeightCharging() {
        return weightCharging;
    }

    public void setWeightCharging(String weightCharging) {
        this.weightCharging = weightCharging == null ? null : weightCharging.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }
}