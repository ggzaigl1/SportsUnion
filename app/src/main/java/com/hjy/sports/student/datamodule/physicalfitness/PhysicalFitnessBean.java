package com.hjy.sports.student.datamodule.physicalfitness;

/**
 * Created by QKun on 2017/12/25.
 */

public class PhysicalFitnessBean {
    private String body;
    private String describe;
    private double curData;


    public PhysicalFitnessBean(String body, String describe, double curData) {
        this.body = body;
        this.describe = describe;
        this.curData = curData;
    }

    public PhysicalFitnessBean() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getCurData() {
        return curData;
    }

    public void setCurData(double curData) {
        this.curData = curData;
    }
}
