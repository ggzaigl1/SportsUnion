package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 自我检测 提交数据 返回实体类
 * Created by fangs on 2018/2/1.
 */
public class SelfDetectionAdd implements Serializable {
    /**
     * items : [{"name":"BMI","type":"","value":"93.75"},{"name":"肺活量","type":"10.0","value":"92.75"},{"name":"50m","type":"100.0","value":"76.77"},{"name":"坐体位","type":"40.0","value":"80.64"},{"name":"跳绳","type":"10.0","value":"87.59"},{"name":"仰卧起坐","type":"10.0","value":"79.81"},{"name":"总分","type":"32.5","value":"86.67"}]
     * result : 1
     * resultmsg : 你的身体情况优秀, 超过全国儿童平均值较多, 继续保持
     */

    private String result;
    private String resultmsg;
    private List<ItemsBean> items;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable {
        /**
         * name : BMI
         * type :
         * value : 93.75
         */

        private String name;
        private String type;
        private String value;

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
    }
}