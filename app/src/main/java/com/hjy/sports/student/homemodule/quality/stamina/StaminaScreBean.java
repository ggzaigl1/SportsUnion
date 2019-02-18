package com.hjy.sports.student.homemodule.quality.stamina;

import java.io.Serializable;

/**
 * 体能 分项得分 实体类
 * Created by fangs on 2018/1/24.
 */
public class StaminaScreBean implements Serializable{

    private String item = "";//项目名称
    private int    scre;//得分

    public StaminaScreBean() {
    }

    public StaminaScreBean(String item, int scre) {
        this.item = item;
        this.scre = scre;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getScre() {
        return scre;
    }

    public void setScre(int scre) {
        this.scre = scre;
    }

    @Override
    public String toString() {
        return "StaminaScreBean{" +
                "item='" + item + '\'' +
                ", scre=" + scre +
                '}';
    }
}
