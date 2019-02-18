package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/1/27.
 */

public class BodyConToAppBean {

    /**
     * pingjia : 超重型
     * tendency : [{"name":"五年级","value":"20.7"}]
     * pingjia2 : 按照身高体重BMI评价方法，你的体重已经超重，要改变自己的饮食习惯阿！
     * fenxi : 你是超重型，超重影响身体形态、生理功能和运动能力的发展，增加代谢相关疾病风险。需调整膳食结构、控制每日能量摄入，减少高脂肪、高能量食物的摄入。养成良好的饮食习惯，不暴饮暴食，保持适宜体重，并结合身体素质短板有目的地优化蛋白质、维生素和矿物质摄取。
     * buchong : [{"id":3,"supplementaryName":"形体训练3","supplementaryContent":"形体训练队学生的健康有好处","image":null,"status":"1","createdTime":"2018-01-27 00:00:00","typeId":"3"}]
     */

    private String pingjia = "";
    private String pingjia2 = "";
    private String fenxi = "";
    private List<TendencyBean> tendency;
    private List<BuchongBean> buchong;

    public String getPingjia() {
        return pingjia;
    }

    public void setPingjia(String pingjia) {
        this.pingjia = pingjia;
    }

    public String getPingjia2() {
        return pingjia2;
    }

    public void setPingjia2(String pingjia2) {
        this.pingjia2 = pingjia2;
    }

    public String getFenxi() {
        return fenxi;
    }

    public void setFenxi(String fenxi) {
        this.fenxi = fenxi;
    }

    public List<TendencyBean> getTendency() {
        return tendency;
    }

    public void setTendency(List<TendencyBean> tendency) {
        this.tendency = tendency;
    }

    public List<BuchongBean> getBuchong() {
        return buchong;
    }

    public void setBuchong(List<BuchongBean> buchong) {
        this.buchong = buchong;
    }

    public static class TendencyBean {
        /**
         * name : 五年级
         * value : 20.7
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

    public static class BuchongBean {
        /**
         * id : 3
         * supplementaryName : 形体训练3
         * supplementaryContent : 形体训练队学生的健康有好处
         * image : null
         * status : 1
         * createdTime : 2018-01-27 00:00:00
         * typeId : 3
         */

        private int id;
        private String supplementaryName = "";
        private String supplementaryContent = "";
        private String image = "";
        private String status = "";
        private String createdTime = "";
        private String typeId = "";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSupplementaryName() {
            return supplementaryName;
        }

        public void setSupplementaryName(String supplementaryName) {
            this.supplementaryName = supplementaryName;
        }

        public String getSupplementaryContent() {
            return supplementaryContent;
        }

        public void setSupplementaryContent(String supplementaryContent) {
            this.supplementaryContent = supplementaryContent;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}
