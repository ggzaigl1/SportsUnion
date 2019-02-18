package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/2/1.
 */

public class HealthyFoodBean {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":3,"recipesName":"火腿肌肉饭","type":"1","mealType":"1","recipesType":"2","ageMix":"11","ageMax":"13","foodValue":"高蛋白，氨基酸","recipesContent":"玉米粉条67g,绿甘蓝\t100g","image":null}]
     * total : 4
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
         * id : 3
         * recipesName : 火腿肌肉饭
         * type : 1
         * mealType : 1
         * recipesType : 2
         * ageMix : 11
         * ageMax : 13
         * foodValue : 高蛋白，氨基酸
         * recipesContent : 玉米粉条67g,绿甘蓝	100g
         * image : null
         */

        private int id;
        private String recipesName;
        private String type;
        private String mealType;
        private String recipesType;
        private String ageMix;
        private String ageMax;
        private String foodValue;
        private String recipesContent;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRecipesName() {
            return recipesName;
        }

        public void setRecipesName(String recipesName) {
            this.recipesName = recipesName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public String getRecipesType() {
            return recipesType;
        }

        public void setRecipesType(String recipesType) {
            this.recipesType = recipesType;
        }

        public String getAgeMix() {
            return ageMix;
        }

        public void setAgeMix(String ageMix) {
            this.ageMix = ageMix;
        }

        public String getAgeMax() {
            return ageMax;
        }

        public void setAgeMax(String ageMax) {
            this.ageMax = ageMax;
        }

        public String getFoodValue() {
            return foodValue;
        }

        public void setFoodValue(String foodValue) {
            this.foodValue = foodValue;
        }

        public String getRecipesContent() {
            return recipesContent;
        }

        public void setRecipesContent(String recipesContent) {
            this.recipesContent = recipesContent;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
