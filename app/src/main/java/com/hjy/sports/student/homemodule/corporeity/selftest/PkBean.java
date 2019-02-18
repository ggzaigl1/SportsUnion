package com.hjy.sports.student.homemodule.corporeity.selftest;

/**
 * Created by Administrator on 2018/1/26.
 */

public class PkBean {

    private float leftProgress;
    private float rightProgress;

    private int leftHeight; //自己得分
    private int rightHeight; //全国平均值
    private String name;

//    public PkBean(int leftProgress, int rightProgress, int leftHeight, int rightHeight) {
//        this.leftProgress = leftProgress;
//        this.rightProgress = rightProgress;
//        this.leftHeight = leftHeight;
//        this.rightHeight = rightHeight;
//    }

    public PkBean(float leftProgress, float rightProgress, String name) {
        this.leftProgress = leftProgress;
        this.rightProgress = rightProgress;
        this.name = name;
    }

    public float getLeftProgress() {
        return leftProgress;
    }

    public void setLeftProgress(float leftProgress) {
        this.leftProgress = leftProgress;
    }

    public float getRightProgress() {
        return rightProgress;
    }

    public void setRightProgress(float rightProgress) {
        this.rightProgress = rightProgress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
