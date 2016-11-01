package com.yogapay.couriertsi.domain;

public class OrderRoute {
    private Integer id;

    private String orderNo;

    private String sub1;

    private String sub2;

    private String sub3;

    private String sub4;

    private String sub5;

    private String sub6;

    private String subRoute;

    private Double sub1Profit;

    private Double sub2Profit;

    private Double sub3Profit;

    private Double sub4Profit;

    private Double sub5Profit;

    private Double sub6Profit;

    private String sub;

    private Double subNormalProfit;

    private Double subPackProfit;

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

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1 == null ? null : sub1.trim();
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2 == null ? null : sub2.trim();
    }

    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3 == null ? null : sub3.trim();
    }

    public String getSub4() {
        return sub4;
    }

    public void setSub4(String sub4) {
        this.sub4 = sub4 == null ? null : sub4.trim();
    }

    public String getSub5() {
        return sub5;
    }

    public void setSub5(String sub5) {
        this.sub5 = sub5 == null ? null : sub5.trim();
    }

    public String getSub6() {
        return sub6;
    }

    public void setSub6(String sub6) {
        this.sub6 = sub6 == null ? null : sub6.trim();
    }

    public String getSubRoute() {
        return subRoute;
    }

    public void setSubRoute(String subRoute) {
        this.subRoute = subRoute == null ? null : subRoute.trim();
    }

    public Double getSub1Profit() {
        return sub1Profit;
    }

    public void setSub1Profit(Double sub1Profit) {
        this.sub1Profit = sub1Profit;
    }

    public Double getSub2Profit() {
        return sub2Profit;
    }

    public void setSub2Profit(Double sub2Profit) {
        this.sub2Profit = sub2Profit;
    }

    public Double getSub3Profit() {
        return sub3Profit;
    }

    public void setSub3Profit(Double sub3Profit) {
        this.sub3Profit = sub3Profit;
    }

    public Double getSub4Profit() {
        return sub4Profit;
    }

    public void setSub4Profit(Double sub4Profit) {
        this.sub4Profit = sub4Profit;
    }

    public Double getSub5Profit() {
        return sub5Profit;
    }

    public void setSub5Profit(Double sub5Profit) {
        this.sub5Profit = sub5Profit;
    }

    public Double getSub6Profit() {
        return sub6Profit;
    }

    public void setSub6Profit(Double sub6Profit) {
        this.sub6Profit = sub6Profit;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub == null ? null : sub.trim();
    }

    public Double getSubNormalProfit() {
        return subNormalProfit;
    }

    public void setSubNormalProfit(Double subNormalProfit) {
        this.subNormalProfit = subNormalProfit;
    }

    public Double getSubPackProfit() {
        return subPackProfit;
    }

    public void setSubPackProfit(Double subPackProfit) {
        this.subPackProfit = subPackProfit;
    }
}