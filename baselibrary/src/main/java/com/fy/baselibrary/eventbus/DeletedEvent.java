package com.fy.baselibrary.eventbus;

import java.io.Serializable;

/**
 * Created by QKun on 2018/3/2.
 */

public class DeletedEvent implements Serializable {
    private int socialid;

    public DeletedEvent(int socialid) {
        this.socialid = socialid;
    }

    public int getSocialid() {
        return socialid;
    }

    public void setSocialid(int socialid) {
        this.socialid = socialid;
    }
}
