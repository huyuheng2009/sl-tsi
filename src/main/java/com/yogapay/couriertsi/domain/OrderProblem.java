package com.yogapay.couriertsi.domain;

import java.util.Date;

public class OrderProblem {
    private Integer id;

    private String reamrk;

    private String img;

    private String reason;

    private Integer type;

    private String orderNo;

    private Date createTime;

    private Integer status;

    private String registSno;

    private String result;

    private String resultRemark;

    private String registOperate;

    private String resultOperate;

    private String resultSno;

    private Date resultTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReamrk() {
        return reamrk;
    }

    public void setReamrk(String reamrk) {
        this.reamrk = reamrk == null ? null : reamrk.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegistSno() {
        return registSno;
    }

    public void setRegistSno(String registSno) {
        this.registSno = registSno == null ? null : registSno.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getResultRemark() {
        return resultRemark;
    }

    public void setResultRemark(String resultRemark) {
        this.resultRemark = resultRemark == null ? null : resultRemark.trim();
    }

    public String getRegistOperate() {
        return registOperate;
    }

    public void setRegistOperate(String registOperate) {
        this.registOperate = registOperate == null ? null : registOperate.trim();
    }

    public String getResultOperate() {
        return resultOperate;
    }

    public void setResultOperate(String resultOperate) {
        this.resultOperate = resultOperate == null ? null : resultOperate.trim();
    }

    public String getResultSno() {
        return resultSno;
    }

    public void setResultSno(String resultSno) {
        this.resultSno = resultSno == null ? null : resultSno.trim();
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }
}