package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/2/24.
 */

public class EmergencyTreatmentBean implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":1,"name":"擦伤","describe":null,"level":"一级","expression":"皮肤的表皮擦伤。","dispose":"擦伤部位较浅，涂红药水即可；如擦伤创面较脏或有渗血，应用生理盐水清创后再涂红药水或紫药水。","index":1,"thumImage":"","image":""},{"id":2,"name":"挫伤","describe":null,"level":"一级","expression":"身体局部受到钝器打击引起的组织损伤。","dispose":"轻度损伤不需特殊处理，冷敷24小时后可用活血化瘀叮剂，局部可用伤湿止痛膏。伤后第一天冷敷，第二天热敷。约一周后可吸收消失。较重时可用云南白药加白酒调敷伤处并包扎，隔日换药一次，每日2-3次，加理疗。","index":2,"thumImage":"","image":""},{"id":3,"name":"关节扭伤","describe":null,"level":"二级","expression":"主要表现为损伤部位疼痛肿胀和关节活动受限，多发于腰、踝、膝、肩、腕、肘、髋等部位。","dispose":"急性期（24小时前）：停止运动、冷敷、包扎、抬高受伤部位。","index":3,"thumImage":"","image":""},{"id":4,"name":"运动疲劳","describe":null,"level":"二级","expression":"心悸、心动过速，运动后血压、脉搏恢复慢，内脏不适、血尿等，发冷，多汗、脸色白或红、头痛、晕、虚、筋疲力尽。","dispose":"调整锻炼计划，运动量，循序渐进、系统训练、全面训练","index":4,"thumImage":"","image":""},{"id":5,"name":"运动腹痛","describe":null,"level":"二级","expression":"1、肝脾淤血，慢性腹部疾病。 2、呼吸肌痉挛（准备活动不够，肺透气低，运动与呼吸不协调）。 3、胃肠痉挛（运动前吃得过饱、饭后过早运动，空腹或喝水太多）。","dispose":"减慢运动速度、加深呼吸、调整运动呼吸节奏、手按疼痛部位，实在不行停止运动。口服减痉挛药物（阿托品、十滴水）。","index":5,"thumImage":"","image":""},{"id":6,"name":"脚底筋膜炎和神经刺痛","describe":null,"level":"二级","expression":"脚底频繁压力过多产生的疼痛。","dispose":"注意放松、休息、按摩、热水澡。","index":6,"thumImage":"","image":""},{"id":7,"name":"心绞痛","describe":null,"level":"二级","expression":"心绞痛经常表现在腿和腹部的疼痛和抽筋现象。","dispose":"休息，让练习者在良好的环境锻炼","index":7,"thumImage":"","image":""}]
     * total : 7
     * totalPages : 1
     * first : 1
     */

    private int offset;
    private int limit;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private int first;
    private List<RowsBean> rows;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * id : 1
         * name : 擦伤
         * describe : null
         * level : 一级
         * expression : 皮肤的表皮擦伤。
         * dispose : 擦伤部位较浅，涂红药水即可；如擦伤创面较脏或有渗血，应用生理盐水清创后再涂红药水或紫药水。
         * index : 1
         * thumImage :
         * image :
         */


        private int id;
        private String name;
        private Object describe;
        private String level;
        private String expression;
        private String dispose;
        private int index;
        private String thumImage;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getDescribe() {
            return describe;
        }

        public void setDescribe(Object describe) {
            this.describe = describe;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getDispose() {
            return dispose;
        }

        public void setDispose(String dispose) {
            this.dispose = dispose;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getThumImage() {
            return thumImage;
        }

        public void setThumImage(String thumImage) {
            this.thumImage = thumImage;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "RowsBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", describe=" + describe +
                    ", level='" + level + '\'' +
                    ", expression='" + expression + '\'' +
                    ", dispose='" + dispose + '\'' +
                    ", index=" + index +
                    ", thumImage='" + thumImage + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }
    }

}
