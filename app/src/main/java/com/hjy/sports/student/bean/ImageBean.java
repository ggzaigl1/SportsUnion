package com.hjy.sports.student.bean;

/**
 * Created by QKun on 2017/12/15.
 */

public class ImageBean {

    private int id;
    private String name;

    public ImageBean() {
    }

    public ImageBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getDrawable() {
        return id;
    }

    public void setDrawable(int drawable) {
        this.id = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "drawable=" + id +
                ", name=" + name +
                '}';
    }
}
