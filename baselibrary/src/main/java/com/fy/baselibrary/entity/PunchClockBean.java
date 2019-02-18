package com.fy.baselibrary.entity;

/**
 * Created by QKun on 2018/2/2.
 */

public class PunchClockBean {


    /**
     * id : 1
     * studentid : 34760
     * clockDate : 2018-02-01 00:00:00
     * sportsMethodId : 1
     * sportsMethodName : 雄鹰表
     * score : 2
     */

    private int id;
    private String studentid;
    private String clockDate;
    private int sportsMethodId;
    private String sportsMethodName;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getClockDate() {
        return clockDate;
    }

    public void setClockDate(String clockDate) {
        this.clockDate = clockDate;
    }

    public int getSportsMethodId() {
        return sportsMethodId;
    }

    public void setSportsMethodId(int sportsMethodId) {
        this.sportsMethodId = sportsMethodId;
    }

    public String getSportsMethodName() {
        return sportsMethodName;
    }

    public void setSportsMethodName(String sportsMethodName) {
        this.sportsMethodName = sportsMethodName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
