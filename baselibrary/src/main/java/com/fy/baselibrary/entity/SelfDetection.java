package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * 自我检测 条目数据 实体类
 * Created by fangs on 2018/2/1.
 */
public class SelfDetection implements Serializable {

    public SelfDetection() {
    }

    public SelfDetection(String name, String value, String inputContent) {
        this.name = name;
        this.value = value;
        this.inputContent = inputContent;
    }

    /**
     * name : 身高(cm)
     * value : height
     */

    private String name = "";
    private String value = "";
    private String inputContent = "";

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

    public String getInputContent() {
        return inputContent;
    }

    public void setInputContent(String inputContent) {
        this.inputContent = inputContent;
    }

    @Override
    public String toString() {
        return "SelfDetection{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", inputContent='" + inputContent + '\'' +
                '}';
    }
}
