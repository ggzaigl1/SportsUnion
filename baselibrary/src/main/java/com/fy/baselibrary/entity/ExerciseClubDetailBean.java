package com.fy.baselibrary.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by QKun on 2018/3/9.
 */

public class ExerciseClubDetailBean implements Serializable {

    /**
     * id : 6
     * name : A足球俱乐部
     * jieshao : A足球俱乐部
     * itemId : 2
     * itemName : 足球
     * image : /web/f336cd3260314fa78d1289ffea54245a.png
     * exerciseClubId : 6
     * exerciseCommentList : [{"id":1,"clubId":6,"clubName":"A足球俱乐部","studentName":null,"studentid":34733,"content":"我是评论","image":"/1231.jpg","sendDate":null}]
     * exerciseCurriculumList : [{"id":2,"clubId":6,"clubName":"231","name":"初级课程","jieshao":"初级课程","jiaolian":"124","jiaolianJieshao":"1241","zhuyi":"421","image":"/web/cb884aa1cee54e9583c12276e9cb4453.png"},{"id":3,"clubId":6,"clubName":"231","name":"中级课程","jieshao":"中级课程","jiaolian":"中级课程","jiaolianJieshao":"中级课程","zhuyi":"中级课程","image":""}]
     */

    private int id;
    private String name;
    private String jieshao;
    private String itemId;
    private String itemName;
    private String image;
    private int exerciseClubId;
    private List<ExerciseCommentListBean> exerciseCommentList;
    private List<ExerciseCurriculumListBean> exerciseCurriculumList;

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

    public List<ExerciseCommentListBean> getExerciseCommentList() {
        return exerciseCommentList;
    }

    public void setExerciseCommentList(List<ExerciseCommentListBean> exerciseCommentList) {
        this.exerciseCommentList = exerciseCommentList;
    }

    public List<ExerciseCurriculumListBean> getExerciseCurriculumList() {
        return exerciseCurriculumList;
    }

    public void setExerciseCurriculumList(List<ExerciseCurriculumListBean> exerciseCurriculumList) {
        this.exerciseCurriculumList = exerciseCurriculumList;
    }

    public static class ExerciseCommentListBean implements Serializable {
        /**
         * id : 1
         * clubId : 6
         * clubName : A足球俱乐部
         * studentName : null
         * studentid : 34733
         * content : 我是评论
         * image : /1231.jpg
         * sendDate : null
         */

        private int id;
        private int clubId;
        private String clubName;
        private String studentName;
        private int studentid;
        private String content;
        private String image;
        private String sendDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClubId() {
            return clubId;
        }

        public void setClubId(int clubId) {
            this.clubId = clubId;
        }

        public String getClubName() {
            return clubName;
        }

        public void setClubName(String clubName) {
            this.clubName = clubName;
        }

        public Object getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }
    }

    public static class ExerciseCurriculumListBean implements Serializable{
        /**
         * id : 2
         * clubId : 6
         * clubName : 231
         * name : 初级课程
         * jieshao : 初级课程
         * jiaolian : 124
         * jiaolianJieshao : 1241
         * zhuyi : 421
         * image : /web/cb884aa1cee54e9583c12276e9cb4453.png
         */

        private int id;
        private int clubId;
        private String clubName;
        private String name;
        private String jieshao;
        private String jiaolian;
        private String jiaolianJieshao;
        private String zhuyi;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getClubId() {
            return clubId;
        }

        public void setClubId(int clubId) {
            this.clubId = clubId;
        }

        public String getClubName() {
            return clubName;
        }

        public void setClubName(String clubName) {
            this.clubName = clubName;
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

        public String getJiaolian() {
            return jiaolian;
        }

        public void setJiaolian(String jiaolian) {
            this.jiaolian = jiaolian;
        }

        public String getJiaolianJieshao() {
            return jiaolianJieshao;
        }

        public void setJiaolianJieshao(String jiaolianJieshao) {
            this.jiaolianJieshao = jiaolianJieshao;
        }

        public String getZhuyi() {
            return zhuyi;
        }

        public void setZhuyi(String zhuyi) {
            this.zhuyi = zhuyi;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
