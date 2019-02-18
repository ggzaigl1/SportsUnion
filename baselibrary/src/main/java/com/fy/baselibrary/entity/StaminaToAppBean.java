package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/1/27.
 */
public class StaminaToAppBean {
    /**
     * staminalist : [{"name":"灵敏","type":"5","value":"优秀"}]
     * stamina : 94
     * weak : [{"name":"肺活量的数值低","value":"说明机体摄氧能力和排出废气的能力差。一旦机体需要大量消耗氧的情况，例如长时间学习、工作、剧烈运动时，就会出现氧供应的严重不足，从而导致诸如注意力不集中、记忆力下降、头痛、头晕、胸闷等不良反映。体育锻炼可以提高肺活量，如耐久跑、游泳、踢足球、打篮球、游泳等。"}]
     */

    private String stamina = "";
    private List<StaminalistBean> staminalist;
    private List<WeakBean> weak;

    public String getStamina() {
        return stamina;
    }

    public void setStamina(String stamina) {
        this.stamina = stamina;
    }

    public List<StaminalistBean> getStaminalist() {
        return staminalist;
    }

    public void setStaminalist(List<StaminalistBean> staminalist) {
        this.staminalist = staminalist;
    }

    public List<WeakBean> getWeak() {
        return weak;
    }

    public void setWeak(List<WeakBean> weak) {
        this.weak = weak;
    }

    public static class StaminalistBean {
        /**
         * name : 灵敏
         * type : 5
         * value : 优秀
         */

        private String name = "";
        private String type = "";
        private String value = "";
        private int itemType = 0;//区分 多布局 0：center；1：head；2：head foot;

        public StaminalistBean(int itemType) {
            this.itemType = itemType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static class WeakBean {
        /**
         * name : 肺活量的数值低
         * value : 说明机体摄氧能力和排出废气的能力差。一旦机体需要大量消耗氧的情况，例如长时间学习、工作、剧烈运动时，就会出现氧供应的严重不足，从而导致诸如注意力不集中、记忆力下降、头痛、头晕、胸闷等不良反映。体育锻炼可以提高肺活量，如耐久跑、游泳、踢足球、打篮球、游泳等。
         */

        private String name = "";
        private String value = "";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
