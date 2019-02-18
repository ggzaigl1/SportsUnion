package com.fy.baselibrary.eventbus;

/**
 * Created by QKun on 2018/1/5.
 */

public class PublishEvent {
    private String publish;

    public PublishEvent(String publish) {
        this.publish = publish;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
