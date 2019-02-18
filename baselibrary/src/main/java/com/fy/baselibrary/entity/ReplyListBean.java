package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2017/12/29.
 */

public class ReplyListBean implements Serializable{


    /**
     * first : 1
     * limit : 2147483647
     * offset : 0
     * pageNo : 1
     * pageSize : 10
     * rows : [{"content":"bbbbbbbb","id":2,"imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png","parentid":3,"senddate":"2017-12-28 13:54","socialSonList":[{"content":"我是回复内容","id":4,"imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png","parentid":2,"socialSonid":4,"studentid":3,"studentname":"白桐瑞","touxiangurl":"/touxiang/20171228/20bd473ba6d8456caae0bde2600eb5e9.jpg","type":2}],"socialSonid":2,"studentid":3,"studentname":"白桐瑞","touxiangurl":"/touxiang/20171228/20bd473ba6d8456caae0bde2600eb5e9.jpg","type":1}]
     * total : 2
     * totalPages : 1
     */

    private int first;
    private int limit;
    private int offset;
    private int pageNo;
    private int pageSize;
    private int total;
    private int totalPages;
    private List<RowsBean> rows;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * content : bbbbbbbb
         * id : 2
         * imageurl : /touxiang/3239f73428d740a8954ec51aa615b13f.png
         * parentid : 3
         * senddate : 2017-12-28 13:54
         * socialSonList : [{"content":"我是回复内容","id":4,"imageurl":"/touxiang/3239f73428d740a8954ec51aa615b13f.png","parentid":2,"socialSonid":4,"studentid":3,"studentname":"白桐瑞","touxiangurl":"/touxiang/20171228/20bd473ba6d8456caae0bde2600eb5e9.jpg","type":2}]
         * socialSonid : 2
         * studentid : 3
         * studentname : 白桐瑞
         * touxiangurl : /touxiang/20171228/20bd473ba6d8456caae0bde2600eb5e9.jpg
         * type : 1
         */

        private String content;
        private int id;
        private String imageurl;
        private int parentid;
        private String senddate;
        private int socialSonid;
        private int studentid;
        private String studentname;
        private String touxiangurl;
        private int type;
        private List<SocialSonListBean> socialSonList;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public String getSenddate() {
            return senddate;
        }

        public void setSenddate(String senddate) {
            this.senddate = senddate;
        }

        public int getSocialSonid() {
            return socialSonid;
        }

        public void setSocialSonid(int socialSonid) {
            this.socialSonid = socialSonid;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getTouxiangurl() {
            return touxiangurl;
        }

        public void setTouxiangurl(String touxiangurl) {
            this.touxiangurl = touxiangurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<SocialSonListBean> getSocialSonList() {
            return socialSonList;
        }

        public void setSocialSonList(List<SocialSonListBean> socialSonList) {
            this.socialSonList = socialSonList;
        }

        public static class SocialSonListBean implements Serializable{
            /**
             * content : 我是回复内容
             * id : 4
             * imageurl : /touxiang/3239f73428d740a8954ec51aa615b13f.png
             * parentid : 2
             * socialSonid : 4
             * studentid : 3
             * studentname : 白桐瑞
             * touxiangurl : /touxiang/20171228/20bd473ba6d8456caae0bde2600eb5e9.jpg
             * type : 2
             */



            private String content;
            private int id;
            private int parentid;
            private int socialSonid;
            private int studentid;
            private String studentname;
            private String touxiangurl;
            private int type;
            private int targetid;
            private String targetname;
            private String senddate;

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

            public String getSenddate() {
                return senddate;
            }

            public void setSenddate(String senddate) {
                this.senddate = senddate;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentid() {
                return parentid;
            }

            public void setParentid(int parentid) {
                this.parentid = parentid;
            }

            public int getSocialSonid() {
                return socialSonid;
            }

            public void setSocialSonid(int socialSonid) {
                this.socialSonid = socialSonid;
            }

            public int getStudentid() {
                return studentid;
            }

            public void setStudentid(int studentid) {
                this.studentid = studentid;
            }

            public String getStudentname() {
                return studentname;
            }

            public void setStudentname(String studentname) {
                this.studentname = studentname;
            }

            public String getTouxiangurl() {
                return touxiangurl;
            }

            public void setTouxiangurl(String touxiangurl) {
                this.touxiangurl = touxiangurl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

        }
    }
}
