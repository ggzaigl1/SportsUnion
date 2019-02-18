package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/1/27.
 */

public class StandardsBean {

    private List<ItemsBean> items;
    private List<WeakBean> weak;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<WeakBean> getWeak() {
        return weak;
    }

    public void setWeak(List<WeakBean> weak) {
        this.weak = weak;
    }

    public static class ItemsBean {
        /**
         * name : 达标成绩
         * value : 72.0
         */

        private String name;
        private String value;
        private int itemType = 0;//区分 多布局 0：center；1：head；2：head foot;

        public ItemsBean(int itemType) {
            this.itemType = itemType;
        }

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
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static class WeakBean {
        /**
         * name : 50米跑的数值高
         * value : 说明速度素质较差。可能有几个因素所致：起跑技术不对、腿部力量不足、跑步姿势不正确、上下肢配合不协调等。正确的练习和长期的坚持，可以有效提高50米跑的成绩。
         */

        private String name;
        private String value;

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
