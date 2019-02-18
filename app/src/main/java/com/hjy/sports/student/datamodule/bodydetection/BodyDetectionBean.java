package com.hjy.sports.student.datamodule.bodydetection;

/**
 * Created by QKun on 2017/12/19.
 */

public class BodyDetectionBean {
    private String body;
    private String describe;
    private double curData;
    private double datapd;

    public BodyDetectionBean(String body, String describe, double curData, double datapd) {
        this.body = body;
        this.describe = describe;
        this.curData = curData;
        this.datapd = datapd;
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

    public double getDatapd() {
        return datapd;
    }

    public void setDatapd(double datapd) {
        this.datapd = datapd;
    }
}
