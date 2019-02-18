package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2018/2/2 0002.
 * 测试记录
 */

public class SelfChallengeRecord implements Serializable {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 20
     * rows : [{"id":130,"studentid":34672,"xiangmu":"引体向上","xiangmuscore":"50","xiangmufilepath":"/selfchallenge/20180307/c580d14b15d74b02bb41bd000fcca997.jpg","status":"1","shenpiren":"yy","xiangmupici":"2018030717513051288","createdtime":"2018-03-07 17:51:31"},{"id":121,"studentid":34672,"xiangmu":"原地纵跳摸高","xiangmuscore":null,"xiangmufilepath":"/selfchallenge/20180307/e22fae859d354dd897358a30af9b113d.jpg","status":"0","shenpiren":null,"xiangmupici":"2018030710210970173","createdtime":"2018-03-07 10:21:10"},{"id":120,"studentid":34672,"xiangmu":"俯卧撑","xiangmuscore":null,"xiangmufilepath":"/selfchallenge/20180307/ec6bfb18ca8a4a66bd060debe95596b6.png","status":"0","shenpiren":null,"xiangmupici":"2018030710184595129","createdtime":"2018-03-07 10:18:45"}]
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

    public static class RowsBean implements Serializable {
        /**
         * id : 130
         * studentid : 34672
         * xiangmu : 引体向上
         * xiangmuscore : 50
         * xiangmufilepath : /selfchallenge/20180307/c580d14b15d74b02bb41bd000fcca997.jpg
         * status : 1
         * shenpiren : yy
         * xiangmupici : 2018030717513051288
         * createdtime : 2018-03-07 17:51:31
         */

        private int id;
        private int studentid;
        private String xiangmu;
        private String xiangmuscore;
        private String xiangmufilepath;
        private String status;
        private String shenpiren;
        private String xiangmupici;
        private String createdtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public String getXiangmu() {
            return xiangmu;
        }

        public void setXiangmu(String xiangmu) {
            this.xiangmu = xiangmu;
        }

        public String getXiangmuscore() {
            return xiangmuscore;
        }

        public void setXiangmuscore(String xiangmuscore) {
            this.xiangmuscore = xiangmuscore;
        }

        public String getXiangmufilepath() {
            return xiangmufilepath;
        }

        public void setXiangmufilepath(String xiangmufilepath) {
            this.xiangmufilepath = xiangmufilepath;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShenpiren() {
            return shenpiren;
        }

        public void setShenpiren(String shenpiren) {
            this.shenpiren = shenpiren;
        }

        public String getXiangmupici() {
            return xiangmupici;
        }

        public void setXiangmupici(String xiangmupici) {
            this.xiangmupici = xiangmupici;
        }

        public String getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(String createdtime) {
            this.createdtime = createdtime;
        }
    }
}
