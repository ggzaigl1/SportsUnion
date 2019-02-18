package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * 上传 自我挑战 数据 实体类
 * Created by fangs on 2018/2/1.
 */
public class SaveSelfChallenge implements Serializable{

    public int studentid;
    public String xiangmu = "";
    public String xiangmufilepath = "";

    public SaveSelfChallenge() {
    }

    public SaveSelfChallenge(int studentid, String xiangmu, String xiangmufilepath) {
        this.studentid = studentid;
        this.xiangmu = xiangmu;
        this.xiangmufilepath = xiangmufilepath;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getXiangmu() {
        return xiangmu;
    }

    public void setXiangmu(String xiangmu) {
        this.xiangmu = xiangmu;
    }

    public String getXiangmufilepath() {
        return xiangmufilepath;
    }

    public void setXiangmufilepath(String xiangmufilepath) {
        this.xiangmufilepath = xiangmufilepath;
    }

    @Override
    public String toString() {
        return "SaveSelfChallenge{" +
                "studentid=" + studentid +
                ", xiangmu='" + xiangmu + '\'' +
                ", xiangmufilepath='" + xiangmufilepath + '\'' +
                '}';
    }
}
