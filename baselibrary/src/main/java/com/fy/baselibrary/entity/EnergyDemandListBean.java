package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/2/8.
 */

public class EnergyDemandListBean {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":10,"type":"1","name":"热量(kcal/d)","units":"","sex":"2","grademin":11,"grademax":13,"gradestr":"4","value":"1800-2000","sort":10},{"id":22,"type":"1","name":"碳水化合物(g/d)","units":"","sex":"2","grademin":11,"grademax":13,"gradestr":"4","value":"225-325","sort":22},{"id":34,"type":"1","name":"脂肪(g/d)","units":"","sex":"2","grademin":11,"grademax":13,"gradestr":"4","value":"40-67","sort":34},{"id":46,"type":"1","name":"蛋白质(g/d)","units":"","sex":"2","grademin":11,"grademax":13,"gradestr":"4","value":"54-68","sort":46}]
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
         * id : 10
         * type : 1
         * name : 热量(kcal/d)
         * units :
         * sex : 2
         * grademin : 11
         * grademax : 13
         * gradestr : 4
         * value : 1800-2000
         * sort : 10
         */

        private int id;
        private String type;
        private String name;
        private String units;
        private String sex;
        private int grademin;
        private int grademax;
        private String gradestr;
        private String value;
        private int sort;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getGrademin() {
            return grademin;
        }

        public void setGrademin(int grademin) {
            this.grademin = grademin;
        }

        public int getGrademax() {
            return grademax;
        }

        public void setGrademax(int grademax) {
            this.grademax = grademax;
        }

        public String getGradestr() {
            return gradestr;
        }

        public void setGradestr(String gradestr) {
            this.gradestr = gradestr;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
