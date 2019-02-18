package com.fy.baselibrary.eventbus;

import java.io.Serializable;

/**
 * Created by QKun on 2018/1/8.
 */

public class RecommendNumEvent implements Serializable{
//    private int index;
    private int recommendNum;
    private int socialid;  //回复帖子的id



    public RecommendNumEvent( int recommendNum, int socialid) {
        this.recommendNum = recommendNum;
        this.socialid = socialid;
    }



    public int getSocialid() {
        return socialid;
    }

    public void setSocialid(int socialid) {
        this.socialid = socialid;
    }


    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }
}
