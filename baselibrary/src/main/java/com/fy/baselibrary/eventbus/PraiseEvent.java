package com.fy.baselibrary.eventbus;

import java.io.Serializable;

/**
 * Created by QKun on 2018/1/5.
 */

public class PraiseEvent implements Serializable {
//    private int index; //角标  记录上级跳转时的位置
    private int islike; // 状态 是否点击 0：自己没有点击 1：自己已点击
    private int praise_num; //被的次数
    private int socialid; //帖子id 无法记住上上级跳转位置时 使用social来确定帖子位置

    public PraiseEvent(int islike, int praise_num, int socialid) {
        this.islike = islike;
        this.praise_num = praise_num;
        this.socialid = socialid;
    }

//    public PraiseEvent(int islike, int praise_num) {
//        this.islike = islike;
//        this.praise_num = praise_num;
//    }


    public int getSocialid() {
        return socialid;
    }

    public void setSocialid(int socialid) {
        this.socialid = socialid;
    }

    public int getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(int praise_num) {
        this.praise_num = praise_num;
    }



    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }
}
