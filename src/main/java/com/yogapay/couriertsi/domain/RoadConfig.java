package com.yogapay.couriertsi.domain;

public class RoadConfig {
    private Integer id;

    private Double minW;

    private Double minL;

    private Double minM1;

    private Double maxM1;

    private Double s1;

    private Double minM2;

    private Double maxM2;

    private Double s2;

    private Double minM3;

    private Double maxM3;

    private Double s3;

    private Double minM4;

    private Double maxM4;

    private Double s4;

    private Double minM5;

    private Double maxM5;

    private Double s5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMinW() {
        return minW;
    }

    public void setMinW(Double minW) {
        this.minW = minW;
    }

    public Double getMinL() {
        return minL;
    }

    public void setMinL(Double minL) {
        this.minL = minL;
    }

    public Double getMinM1() {
        return minM1;
    }

    public void setMinM1(Double minM1) {
        this.minM1 = minM1;
    }

    public Double getMaxM1() {
        return maxM1;
    }

    public void setMaxM1(Double maxM1) {
        this.maxM1 = maxM1;
    }

    public Double getS1() {
        return s1;
    }

    public void setS1(Double s1) {
        this.s1 = s1;
    }

    public Double getMinM2() {
        return minM2;
    }

    public void setMinM2(Double minM2) {
        this.minM2 = minM2;
    }

    public Double getMaxM2() {
        return maxM2;
    }

    public void setMaxM2(Double maxM2) {
        this.maxM2 = maxM2;
    }

    public Double getS2() {
        return s2;
    }

    public void setS2(Double s2) {
        this.s2 = s2;
    }

    public Double getMinM3() {
        return minM3;
    }

    public void setMinM3(Double minM3) {
        this.minM3 = minM3;
    }

    public Double getMaxM3() {
        return maxM3;
    }

    public void setMaxM3(Double maxM3) {
        this.maxM3 = maxM3;
    }

    public Double getS3() {
        return s3;
    }

    public void setS3(Double s3) {
        this.s3 = s3;
    }

    public Double getMinM4() {
        return minM4;
    }

    public void setMinM4(Double minM4) {
        this.minM4 = minM4;
    }

    public Double getMaxM4() {
        return maxM4;
    }

    public void setMaxM4(Double maxM4) {
        this.maxM4 = maxM4;
    }

    public Double getS4() {
        return s4;
    }

    public void setS4(Double s4) {
        this.s4 = s4;
    }

    public Double getMinM5() {
        return minM5;
    }

    public void setMinM5(Double minM5) {
        this.minM5 = minM5;
    }

    public Double getMaxM5() {
        return maxM5;
    }

    public void setMaxM5(Double maxM5) {
        this.maxM5 = maxM5;
    }

    public Double getS5() {
        return s5;
    }

    public void setS5(Double s5) {
        this.s5 = s5;
    }
    
    /**
     *  根据w获取s值
     * @return
     */
    public Double getSBYW(double weight) {
		if (weight>=(minM1==null?0.0:minM1)&&weight<(maxM1==null?0.0:maxM1)) {
			return s1 ;
		}
		if (weight>=(minM2==null?0.0:minM2)&&weight<(maxM2==null?0.0:maxM2)) {
			return s2 ;
		}
		if (weight>=(minM3==null?0.0:minM3)&&weight<(maxM3==null?0.0:maxM3)) {
			return s3 ;
		}
		if (weight>=(minM4==null?0.0:minM4)&&weight<(maxM4==null?0.0:maxM4)) {
			return s4 ;
		}
		if (weight>=(minM5==null?0.0:minM5)&&weight<(maxM5==null?0.0:maxM5)) {
			return s5 ;
		}
    	return 0.0 ;
	}
    
}