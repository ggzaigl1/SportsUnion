package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/1/27.
 */

public class TotalCountBean {


    /**
     * classCount : 5
     * allRanking : 87
     * classRanking : 83
     * gradeRanking : 93
     * appraise : {"name":"强健型","value":"各项身体素质全面发展，身体强健，均衡发展，需努力保持"}
     * trialplotCount : 85
     * szentirylist : [{"name":"总分","value":"104.0"},{"name":"身体形态","value":"100.0"},{"name":"身体机能","value":"100.0"},{"name":"速度素质","value":"100.0"},{"name":"柔韧素质","value":"80.0"},{"name":"耐力素质","value":"100.0"},{"name":"力量素质","value":"80.0"}]
     * allCount : 85
     * gradeCount : 8
     * trialplotRanking : 87
     */

    private String classCount;
    private String allRanking;
    private String classRanking;
    private String gradeRanking;
    private AppraiseBean appraise;
    private String trialplotCount;
    private String allCount;
    private String gradeCount;
    private String trialplotRanking;
    private List<SzentirylistBean> szentirylist;

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public String getAllRanking() {
        return allRanking;
    }

    public void setAllRanking(String allRanking) {
        this.allRanking = allRanking;
    }

    public String getClassRanking() {
        return classRanking;
    }

    public void setClassRanking(String classRanking) {
        this.classRanking = classRanking;
    }

    public String getGradeRanking() {
        return gradeRanking;
    }

    public void setGradeRanking(String gradeRanking) {
        this.gradeRanking = gradeRanking;
    }

    public AppraiseBean getAppraise() {
        return appraise;
    }

    public void setAppraise(AppraiseBean appraise) {
        this.appraise = appraise;
    }

    public String getTrialplotCount() {
        return trialplotCount;
    }

    public void setTrialplotCount(String trialplotCount) {
        this.trialplotCount = trialplotCount;
    }

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public String getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(String gradeCount) {
        this.gradeCount = gradeCount;
    }

    public String getTrialplotRanking() {
        return trialplotRanking;
    }

    public void setTrialplotRanking(String trialplotRanking) {
        this.trialplotRanking = trialplotRanking;
    }

    public List<SzentirylistBean> getSzentirylist() {
        return szentirylist;
    }

    public void setSzentirylist(List<SzentirylistBean> szentirylist) {
        this.szentirylist = szentirylist;
    }

    public static class AppraiseBean {
        /**
         * name : 强健型
         * value : 各项身体素质全面发展，身体强健，均衡发展，需努力保持
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class SzentirylistBean {
        /**
         * name : 总分
         * value : 104.0
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
