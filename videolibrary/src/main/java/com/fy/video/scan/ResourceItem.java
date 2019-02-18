package com.fy.video.scan;

import java.io.Serializable;

/**
 * 资源（文件）详细信息 （如：一个视频，一张图片等）
 * Created by fangs on 2018/1/27.
 */
public class ResourceItem implements Serializable{

    public String name;       //文件的名字
    public String path;       //文件的路径
    public long size = 0;     //文件的大小
    public int width;         //文件的宽度
    public int height;        //文件的高度
    public String mimeType;   //文件的类型
    public long addTime;      //文件的创建时间

    public boolean isSelect;  //是否选中

    public int isShowCamera = 1;   //是否显示拍照按钮 1：表示不显示；0：显示

    public ResourceItem() {
    }

    public ResourceItem(int isShowCamera) {
        this.isShowCamera = isShowCamera;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int isShowCamera() {
        return isShowCamera;
    }

    public void setShowCamera(int showCamera) {
        isShowCamera = showCamera;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getIsShowCamera() {
        return isShowCamera;
    }

    public void setIsShowCamera(int isShowCamera) {
        this.isShowCamera = isShowCamera;
    }

    /**
     * 文件的路径和创建时间相同就认为是同一个文件
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ResourceItem) {
            ResourceItem item = (ResourceItem) o;
            return this.path.equalsIgnoreCase(item.path) && this.addTime == item.addTime;
        }

        return super.equals(o);
    }

}
