package com.hjy.sports.student.homemodule.corporeity.selftest;

import java.io.Serializable;

/**
 * 自我挑战 实体类
 * Created by fangs on 2018/1/28.
 */
public class SelfChallengeBean implements Serializable{

    private String typeName = "";
    private String videoPath = "";

    public SelfChallengeBean(String typeName, String videoPath) {
        this.typeName = typeName;
        this.videoPath = videoPath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
