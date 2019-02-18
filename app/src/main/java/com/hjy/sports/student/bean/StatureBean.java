package com.hjy.sports.student.bean;

/**
 * Created by QKun on 2017/12/18.
 */

public class StatureBean {


    private String grade;
    private String stature;

    public StatureBean(String grade, String stature) {
        this.grade = grade;
        this.stature = stature;
    }

    public StatureBean() {
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStature() {
        return stature;
    }

    public void setStature(String stature) {
        this.stature = stature;
    }
}
