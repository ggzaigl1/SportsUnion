package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * 学生信息 实体类
 * Created by fangs on 2017/12/22.
 */
public class StudentInfo implements Serializable{

    /**
     * studentid : 86
     * xuejihao : 14179757
     * schoolname : 北京市第一小学
     * schoolcode : test
     * schoolid : 1
     * nname : 一年级
     * ncode : 11
     * nid : 5
     * bname : 1班
     * bcode : 1101
     * bid : 6
     * name : 杨景超
     * sex : 1
     * birthday : 2008-08-30 00:00:00
     * carid : null
     * minzu : null
     * address : null
     * jiazhangphone : null
     * xuehao : 14179757
     * touxiangurl : null
     * height : 155
     * weight : 24.4
     * age : 9
     * createdtime : 2017-12-18 11:26:20
     * creater : 4
     */

    private int studentid;
    private String xuejihao = "";
    private String schoolname = "";
    private String schoolcode = "";
    private int schoolid;
    private String nname = "";
    private String ncode = "";
    private int nid;
    private String bname = "";
    private String bcode = "";
    private int bid;
    private String name = "";
    private String sex = "";
    private String birthday = "";
    private String carid = "";
    private String minzu = "";
    private String address = "";
    private String jiazhangphone = "";
    private String xuehao = "";
    private String touxiangurl = "";
    private double height;
    private double weight;
    private int age;
    private String createdtime = "";
    private int creater;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getXuejihao() {
        return xuejihao;
    }

    public void setXuejihao(String xuejihao) {
        this.xuejihao = xuejihao;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchoolcode() {
        return schoolcode;
    }

    public void setSchoolcode(String schoolcode) {
        this.schoolcode = schoolcode;
    }

    public int getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(int schoolid) {
        this.schoolid = schoolid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJiazhangphone() {
        return jiazhangphone;
    }

    public void setJiazhangphone(String jiazhangphone) {
        this.jiazhangphone = jiazhangphone;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getTouxiangurl() {
        return touxiangurl;
    }

    public void setTouxiangurl(String touxiangurl) {
        this.touxiangurl = touxiangurl;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "studentid=" + studentid +
                ", xuejihao='" + xuejihao + '\'' +
                ", schoolname='" + schoolname + '\'' +
                ", schoolcode='" + schoolcode + '\'' +
                ", schoolid=" + schoolid +
                ", nname='" + nname + '\'' +
                ", ncode='" + ncode + '\'' +
                ", nid=" + nid +
                ", bname='" + bname + '\'' +
                ", bcode='" + bcode + '\'' +
                ", bid=" + bid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", carid='" + carid + '\'' +
                ", minzu='" + minzu + '\'' +
                ", address='" + address + '\'' +
                ", jiazhangphone='" + jiazhangphone + '\'' +
                ", xuehao='" + xuehao + '\'' +
                ", touxiangurl='" + touxiangurl + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", createdtime='" + createdtime + '\'' +
                ", creater=" + creater +
                '}';
    }
}
