package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/1/2.
 */

public class SecondRecommendBean implements Serializable{

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":40,"socialSonid":40,"parentid":36,"studentid":135,"xuejihao":null,"studentname":"曾宪博","targetid":null,"targetname":null,"content":"尼玛的","touxiangurl":null,"imageurl":null,"senddate":"2018-01-02 14:38","type":2,"socialSonList":null},{"id":39,"socialSonid":39,"parentid":36,"studentid":135,"xuejihao":null,"studentname":"曾宪博","targetid":null,"targetname":null,"content":"刚刚给","touxiangurl":null,"imageurl":null,"senddate":"2018-01-02 14:38","type":2,"socialSonList":null},{"id":38,"socialSonid":38,"parentid":36,"studentid":135,"xuejihao":null,"studentname":"曾宪博","targetid":null,"targetname":null,"content":"还好还好哈","touxiangurl":null,"imageurl":null,"senddate":"2018-01-02 14:32","type":2,"socialSonList":null}]
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

    public static class RowsBean implements Serializable{
        /**
         * id : 40
         * socialSonid : 40
         * parentid : 36
         * studentid : 135
         * xuejihao : null
         * studentname : 曾宪博
         * targetid : null
         * targetname : null
         * content : 尼玛的
         * touxiangurl : null
         * imageurl : null
         * senddate : 2018-01-02 14:38
         * type : 2
         * socialSonList : null
         */

        private int id;
        private int socialSonid;
        private int parentid;
        private int studentid;
        private String xuejihao;
        private String studentname;
        private int targetid;
        private String targetname;
        private String content;
        private String touxiangurl;
        private String imageurl;
        private String senddate;
        private int type;
        private Object socialSonList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSocialSonid() {
            return socialSonid;
        }

        public void setSocialSonid(int socialSonid) {
            this.socialSonid = socialSonid;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public String getXuejihao() {
            return xuejihao;
        }

        public void setXuejihao(String xuejihao) {
            this.xuejihao = xuejihao;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public int getTargetid() {
            return targetid;
        }

        public void setTargetid(int targetid) {
            this.targetid = targetid;
        }

        public String getTargetname() {
            return targetname;
        }

        public void setTargetname(String targetname) {
            this.targetname = targetname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTouxiangurl() {
            return touxiangurl;
        }

        public void setTouxiangurl(String touxiangurl) {
            this.touxiangurl = touxiangurl;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getSenddate() {
            return senddate;
        }

        public void setSenddate(String senddate) {
            this.senddate = senddate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getSocialSonList() {
            return socialSonList;
        }

        public void setSocialSonList(Object socialSonList) {
            this.socialSonList = socialSonList;
        }
    }
}
