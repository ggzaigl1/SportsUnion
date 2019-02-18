package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/1/29.
 */

public class SensoryOne {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":1,"sportsName":"跳绳","sportsContent":"双手持绳2端连续跳","sportsTime":"30","sportsDifficulty":"2","image":null,"image1":null,"sportsStep":"1跳绳主要是手腕2高度不宜太高3-5cm之间3呼吸要有节奏","sportsMatter":"1跳绳者应穿质地软，质量轻的高帮鞋，避免脚踝受伤2跳绳时需放松，防止扭伤"},{"id":2,"sportsName":"篮球","sportsContent":"双手持球运动","sportsTime":"40","sportsDifficulty":"3","image":null,"image1":null,"sportsStep":null,"sportsMatter":null},{"id":3,"sportsName":"乒乓球","sportsContent":"持球拍双人运动","sportsTime":"30","sportsDifficulty":"3","image":null,"image1":null,"sportsStep":null,"sportsMatter":null}]
     * total : 3
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
         * id : 1
         * sportsName : 跳绳
         * sportsContent : 双手持绳2端连续跳
         * sportsTime : 30
         * sportsDifficulty : 2
         * image : null
         * image1 : null
         * sportsStep : 1跳绳主要是手腕2高度不宜太高3-5cm之间3呼吸要有节奏
         * sportsMatter : 1跳绳者应穿质地软，质量轻的高帮鞋，避免脚踝受伤2跳绳时需放松，防止扭伤
         */

        private int id;
        private String sportsName;
        private String sportsContent;
        private String sportsTime;
        private String sportsDifficulty;
        private Object image;
        private Object image1;
        private String sportsStep;
        private String sportsMatter;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSportsName() {
            return sportsName;
        }

        public void setSportsName(String sportsName) {
            this.sportsName = sportsName;
        }

        public String getSportsContent() {
            return sportsContent;
        }

        public void setSportsContent(String sportsContent) {
            this.sportsContent = sportsContent;
        }

        public String getSportsTime() {
            return sportsTime;
        }

        public void setSportsTime(String sportsTime) {
            this.sportsTime = sportsTime;
        }

        public String getSportsDifficulty() {
            return sportsDifficulty;
        }

        public void setSportsDifficulty(String sportsDifficulty) {
            this.sportsDifficulty = sportsDifficulty;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Object getImage1() {
            return image1;
        }

        public void setImage1(Object image1) {
            this.image1 = image1;
        }

        public String getSportsStep() {
            return sportsStep;
        }

        public void setSportsStep(String sportsStep) {
            this.sportsStep = sportsStep;
        }

        public String getSportsMatter() {
            return sportsMatter;
        }

        public void setSportsMatter(String sportsMatter) {
            this.sportsMatter = sportsMatter;
        }
    }
}
