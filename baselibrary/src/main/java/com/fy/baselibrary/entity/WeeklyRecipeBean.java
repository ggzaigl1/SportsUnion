package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class WeeklyRecipeBean {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":52,"tixing":"3","recipesName":"咖喱鸡饭","week":"1","mealType":"3","agestr":"3","ageMin":11,"ageMax":13,"material":"大米\t85g\n鸡胸肉\t60g\n胡萝卜\t50g\n油菜\t70g\n咖喱\t若干\n\t水果酸奶\n时令水果\t100g\n酸奶\t150ml\n","recipesContent":null,"image":"/web/40f6b9cbc5ec4963813011047a636c1e.png"},{"id":51,"tixing":"3","recipesName":"牛肉芹菜水饺","week":"1","mealType":"2","agestr":"3","ageMin":11,"ageMax":13,"material":"面粉\t110g\n芹菜\t160g\n牛肉\t80g\n调料\t若干\n\t时令水果 100g\n\t坚果 10g\n","recipesContent":null,"image":"/web/11169908055343179b8343362472b77f.png"},{"id":50,"tixing":"3","recipesName":"干锅菜花","week":"1","mealType":"1","agestr":"3","ageMin":11,"ageMax":13,"material":"菜花\t120g\n牛肉\t60g\n调料\t若干\n\t牛奶150ml\n","recipesContent":null,"image":"/web/631f0756938d4750a4a0b4be0af6bb23.png"}]
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
         * id : 52
         * tixing : 3
         * recipesName : 咖喱鸡饭
         * week : 1
         * mealType : 3
         * agestr : 3
         * ageMin : 11
         * ageMax : 13
         * material : 大米	85g
         * 鸡胸肉	60g
         * 胡萝卜	50g
         * 油菜	70g
         * 咖喱	若干
         * 	水果酸奶
         * 时令水果	100g
         * 酸奶	150ml
         * <p>
         * recipesContent : null
         * image : /web/40f6b9cbc5ec4963813011047a636c1e.png
         */

        private int id;
        private String tixing;
        private String recipesName;
        private String week;
        private String mealType;
        private String agestr;
        private int ageMin;
        private int ageMax;
        private String material;
        private Object recipesContent;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTixing() {
            return tixing;
        }

        public void setTixing(String tixing) {
            this.tixing = tixing;
        }

        public String getRecipesName() {
            return recipesName;
        }

        public void setRecipesName(String recipesName) {
            this.recipesName = recipesName;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public String getAgestr() {
            return agestr;
        }

        public void setAgestr(String agestr) {
            this.agestr = agestr;
        }

        public int getAgeMin() {
            return ageMin;
        }

        public void setAgeMin(int ageMin) {
            this.ageMin = ageMin;
        }

        public int getAgeMax() {
            return ageMax;
        }

        public void setAgeMax(int ageMax) {
            this.ageMax = ageMax;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public Object getRecipesContent() {
            return recipesContent;
        }

        public void setRecipesContent(Object recipesContent) {
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
