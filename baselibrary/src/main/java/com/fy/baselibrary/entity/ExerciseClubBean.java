package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/3/9.
 */

public class ExerciseClubBean implements Serializable {


    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":6,"name":"A足球俱乐部","jieshao":"A足球俱乐部","itemId":"2","itemName":"足球","image":"/web/f336cd3260314fa78d1289ffea54245a.png","exerciseClubId":6,"exerciseCommentList":null,"exerciseCurriculumList":null},{"id":1,"name":"B足球俱乐部","jieshao":"足球","itemId":"2","itemName":"足球","image":"/web/d1524b2812ca4134a5fedbf0fec75fa5.png","exerciseClubId":1,"exerciseCommentList":null,"exerciseCurriculumList":null}]
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
         * id : 6
         * name : A足球俱乐部
         * jieshao : A足球俱乐部
         * itemId : 2
         * itemName : 足球
         * image : /web/f336cd3260314fa78d1289ffea54245a.png
         * exerciseClubId : 6
         * exerciseCommentList : null
         * exerciseCurriculumList : null
         */

        private int id;
        private String name;
        private String jieshao;
        private String itemId;
        private String itemName;
        private String image;
        private int exerciseClubId;
        private Object exerciseCommentList;
        private Object exerciseCurriculumList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJieshao() {
            return jieshao;
        }

        public void setJieshao(String jieshao) {
            this.jieshao = jieshao;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getExerciseClubId() {
            return exerciseClubId;
        }

        public void setExerciseClubId(int exerciseClubId) {
            this.exerciseClubId = exerciseClubId;
        }

        public Object getExerciseCommentList() {
            return exerciseCommentList;
        }

        public void setExerciseCommentList(Object exerciseCommentList) {
            this.exerciseCommentList = exerciseCommentList;
        }

        public Object getExerciseCurriculumList() {
            return exerciseCurriculumList;
        }

        public void setExerciseCurriculumList(Object exerciseCurriculumList) {
            this.exerciseCurriculumList = exerciseCurriculumList;
        }
    }
}
