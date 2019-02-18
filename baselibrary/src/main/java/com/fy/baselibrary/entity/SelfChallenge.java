package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Gab on 2018/2/2 0002.
 *
 */

public class SelfChallenge {

    /**
     * currscore : 0
     * datalist : [{"id":14,"studentid":58142,"xiangmu":"俯卧撑","xiangmuscore":"待审核","xiangmufilepath":"/diffusion/media/20180202/20180202140440_91.mp4","status":"0","shenpiren":null,"xiangmupici":"2018020214044014234","createdtime":"2018-02-02 14:04:41"}]
     * totalcore : 0
     */

    private int currscore;
    private int totalcore;
    private List<DatalistBean> datalist;

    public int getCurrscore() {
        return currscore;
    }

    public void setCurrscore(int currscore) {
        this.currscore = currscore;
    }

    public int getTotalcore() {
        return totalcore;
    }

    public void setTotalcore(int totalcore) {
        this.totalcore = totalcore;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean {
        /**
         * id : 14
         * studentid : 58142
         * xiangmu : 俯卧撑
         * xiangmuscore : 待审核
         * xiangmufilepath : /diffusion/media/20180202/20180202140440_91.mp4
         * status : 0
         * shenpiren : null
         * xiangmupici : 2018020214044014234
         * createdtime : 2018-02-02 14:04:41
         */

        private int id;
        private int studentid;
        private String xiangmu;
        private String xiangmuscore;
        private String xiangmufilepath;
        private String status;
        private Object shenpiren;
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

        public Object getShenpiren() {
            return shenpiren;
        }

        public void setShenpiren(Object shenpiren) {
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
