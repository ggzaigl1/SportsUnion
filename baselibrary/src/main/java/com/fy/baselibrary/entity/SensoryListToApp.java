package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stefan on 2018/1/29.
 * 感统
 */

public class SensoryListToApp implements Serializable {

    /**
     * appraise : 优秀
     * pageNo : 1
     * pageSize : 1
     * sensoryPage : [{"content":"孩子双脚分开，与肩同宽，双手在胸前抱住球，向上抛的时候球要过头顶，但是双手不要过肩膀。小学的低年级学生连续抛接20个以上不掉球，提高手眼协调能力。","height":"259","id":4,"image":"/web/ce4c19598cf44ecb9e11999e5ca0467a.jpeg","sort":1,"title":"练习一：抛接球","type":2,"width":"369"}]
     * total : 10
     * totalPages : 10
     */

    private String appraise;
    private String pageNo;
    private String pageSize;
    private String total;
    private String totalPages;
    private List<SensoryPageBean> sensoryPage;

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<SensoryPageBean> getSensoryPage() {
        return sensoryPage;
    }

    public void setSensoryPage(List<SensoryPageBean> sensoryPage) {
        this.sensoryPage = sensoryPage;
    }

    public static class SensoryPageBean implements Serializable{
        /**
         * content : 孩子双脚分开，与肩同宽，双手在胸前抱住球，向上抛的时候球要过头顶，但是双手不要过肩膀。小学的低年级学生连续抛接20个以上不掉球，提高手眼协调能力。
         * height : 259
         * id : 4
         * image : /web/ce4c19598cf44ecb9e11999e5ca0467a.jpeg
         * sort : 1
         * title : 练习一：抛接球
         * type : 2
         * width : 369
         */

        private String content;
        private String height;
        private int id;
        private String image;
        private int sort;
        private String title;
        private int type;
        private String width;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }
}
