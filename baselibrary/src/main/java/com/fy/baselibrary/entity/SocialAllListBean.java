package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2017/12/28.
 */

public class SocialAllListBean implements Serializable {


    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":5,"socialid":5,"studentid":46,"xuejihao":"13191638","studentname":"杨博雯","schoolid":1,"schoolname":"北京市第一小学","content":"我要发贴子","imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png,/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg","imageurlList":["/touxiang/3239f73428d740a8954ec51aa615b13f.png","/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg"],"senddate":"2017-12-28 13:56:13","likenumber":0,"sonnumber":0,"islike":0,"touxiangurl":null,"deletedate":null,"delflag":0,"socialSonList":[]},{"id":4,"socialid":4,"studentid":45,"xuejihao":"13191637","studentname":"步青云","schoolid":1,"schoolname":"北京市第一小学","content":"aa忒嗖嗖嗖aaaaaaaaaa","imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png,/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg","imageurlList":["/touxiang/3239f73428d740a8954ec51aa615b13f.png","/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg"],"senddate":"2017-12-28 13:52:20","likenumber":1,"sonnumber":1,"islike":0,"touxiangurl":null,"deletedate":null,"delflag":0,"socialSonList":[{"id":3,"socialSonid":3,"parentid":4,"studentid":3,"xuejihao":null,"studentname":"白桐瑞","targetid":null,"targetname":null,"content":"我是回复内容","imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png","senddate":"2017-12-28 13:55:13","type":1,"socialSonList":null}]},{"id":3,"socialid":3,"studentid":23,"xuejihao":"13191614","studentname":"李奕祺","schoolid":1,"schoolname":"北京市第一小学","content":"我是帖子内容","imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png,/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg","imageurlList":["/touxiang/3239f73428d740a8954ec51aa615b13f.png","/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg"],"senddate":"2017-12-28 13:51:48","likenumber":0,"sonnumber":1,"islike":0,"touxiangurl":"/touxiang/d21cee37c0f342c6bb7faf3041ec7161.jpg","deletedate":null,"delflag":0,"socialSonList":[{"id":2,"socialSonid":2,"parentid":3,"studentid":3,"xuejihao":null,"studentname":"白桐瑞","targetid":null,"targetname":null,"content":"bbbbbbbb","imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png","senddate":"2017-12-28 13:54:48","type":1,"socialSonList":null}]}]
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
         * id : 5
         * socialid : 5
         * studentid : 46
         * xuejihao : 13191638
         * studentname : 杨博雯
         * schoolid : 1
         * schoolname : 北京市第一小学
         * content : 我要发贴子
         * imageurl : /touxiang/3239f73428d740a8954ec51aa615b13f.png,/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg
         * imageurlList : ["/touxiang/3239f73428d740a8954ec51aa615b13f.png","/web/d7e8c89998744e8581f87fb31e6d5e1d.jpeg"]
         * senddate : 2017-12-28 13:56:13
         * likenumber : 0
         * sonnumber : 0
         * islike : 0
         * touxiangurl : null
         * deletedate : null
         * delflag : 0
         * socialSonList : []
         */

        private int id;
        private int socialid;
        private int studentid;
        private String xuejihao;
        private String studentname;
        private int schoolid;
        private String schoolname;
        private String content;
        private String imageurl;
        private String senddate;
        private int likenumber;
        private int sonnumber;
        private int islike;
        private String touxiangurl;
        private String deletedate;
        private int delflag;
        private List<String> imageurlList;
        private List<?> socialSonList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSocialid() {
            return socialid;
        }

        public void setSocialid(int socialid) {
            this.socialid = socialid;
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

        public int getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(int schoolid) {
            this.schoolid = schoolid;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getLikenumber() {
            return likenumber;
        }

        public void setLikenumber(int likenumber) {
            this.likenumber = likenumber;
        }

        public int getSonnumber() {
            return sonnumber;
        }

        public void setSonnumber(int sonnumber) {
            this.sonnumber = sonnumber;
        }

        public int getIslike() {
            return islike;
        }

        public void setIslike(int islike) {
            this.islike = islike;
        }

        public String getTouxiangurl() {
            return touxiangurl;
        }

        public void setTouxiangurl(String touxiangurl) {
            this.touxiangurl = touxiangurl;
        }

        public String getDeletedate() {
            return deletedate;
        }

        public void setDeletedate(String deletedate) {
            this.deletedate = deletedate;
        }

        public int getDelflag() {
            return delflag;
        }

        public void setDelflag(int delflag) {
            this.delflag = delflag;
        }

        public List<String> getImageurlList() {
            return imageurlList;
        }

        public void setImageurlList(List<String> imageurlList) {
            this.imageurlList = imageurlList;
        }

        public List<?> getSocialSonList() {
            return socialSonList;
        }

        public void setSocialSonList(List<?> socialSonList) {
            this.socialSonList = socialSonList;
        }
    }
}
