package com.fy.video.scan;

import java.io.Serializable;
import java.util.List;

/**
 * 资源文件夹（如：视频文件夹，图片文件夹）
 * Created by fangs on 2018/1/27.
 */
public class ResourceFolder implements Serializable{
    public String name;  //当前文件夹的名字
    public String path;  //当前文件夹的路径
    public ResourceItem cover;          //当前文件夹需要要显示的缩略图，默认为最近的一次图片
    public List<ResourceItem> resources;   //当前文件夹下所有图片的集合

    public ResourceFolder() {
    }

    public ResourceFolder(List<ResourceItem> resources) {
        this.resources = resources;
    }

    /**
     * 只要文件夹的路径和名字相同，就认为是相同的文件夹
     */
    @Override
    public boolean equals(Object o) {
        try {
            ResourceFolder other = (ResourceFolder) o;
            return this.path.equalsIgnoreCase(other.path) && this.name.equalsIgnoreCase(other.name);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return super.equals(o);
    }
}
