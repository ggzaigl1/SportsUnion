package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/3/9.
 */

public class ExerciseItemsBean implements Serializable {


    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":2,"itemName":"足球","jieshao":"我是足球运动","thumImage":"/web/b9b8a50a777b43a5a121f195be90096d.png","image":"/web/33c4fbad8f8a4b7a8e15acdfbcd3ed26.png"},{"id":1,"itemName":"篮球","jieshao":"我是篮球运动","thumImage":"/web/8d8ffe5c8456473f8464b2486da7ba79.png","image":"/web/caf7c752046246d79aebd2bc14c86bbd.png"}]
     * total : 2
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

    public static class RowsBean {
        /**
         * id : 2
         * itemName : 足球
         * jieshao : 我是足球运动
         * thumImage : /web/b9b8a50a777b43a5a121f195be90096d.png
         * image : /web/33c4fbad8f8a4b7a8e15acdfbcd3ed26.png
         */

        private int id;
        private String itemName;
        private String jieshao;
        private String thumImage;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getJieshao() {
            return jieshao;
        }

        public void setJieshao(String jieshao) {
            this.jieshao = jieshao;
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
    }
}
