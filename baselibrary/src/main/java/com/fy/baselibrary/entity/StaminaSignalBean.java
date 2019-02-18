package com.fy.baselibrary.entity;

import java.io.Serializable;

/**
 * Created by Stefan on 2018/1/29.
 * 体能红绿灯
 */

public class StaminaSignalBean implements Serializable {

    /**
     * item : 耐力素质
     * appraise : 优秀
     * yiyi : 耐力素质的意义
     * appraisescope : 100.0
     * yiyicontent : 耐力，也称耐久力，是机体在较长时间内保持特定动作和特定运动强度或的能力，是人体长时间运动中克服疲劳的能力，它是反映人体健康水平和体质强弱的重要标志。耐力水平的提高表现为能更长时间地保持特定运动强度或动作质量，或在同样的时间里承受更高强度运动的能力。发展耐力素质的敏感期在12-16岁左右。
     * lianxi : 耐力素质的练习方式
     * lianxidata : {"id":37,"item":"耐力素质","type":2,"sort":1,"title":"练习一：定时跑","content":"在场地或公路上做10-20分钟或更长时间的定时跑。强度为50％-55％。","image":"/web/76633ac40b274b47a227a486e1565db9.png","width":"233","height":"253"}
     */

    private String item;
    private String appraise;
    private String yiyi;
    private String appraisescope;
    private String yiyicontent;
    private String lianxi;
    private LianxidataBean lianxidata;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getYiyi() {
        return yiyi;
    }

    public void setYiyi(String yiyi) {
        this.yiyi = yiyi;
    }

    public String getAppraisescope() {
        return appraisescope;
    }

    public void setAppraisescope(String appraisescope) {
        this.appraisescope = appraisescope;
    }

    public String getYiyicontent() {
        return yiyicontent;
    }

    public void setYiyicontent(String yiyicontent) {
        this.yiyicontent = yiyicontent;
    }

    public String getLianxi() {
        return lianxi;
    }

    public void setLianxi(String lianxi) {
        this.lianxi = lianxi;
    }

    public LianxidataBean getLianxidata() {
        return lianxidata;
    }

    public void setLianxidata(LianxidataBean lianxidata) {
        this.lianxidata = lianxidata;
    }

    public static class LianxidataBean implements Serializable{
        /**
         * id : 37
         * item : 耐力素质
         * type : 2
         * sort : 1
         * title : 练习一：定时跑
         * content : 在场地或公路上做10-20分钟或更长时间的定时跑。强度为50％-55％。
         * image : /web/76633ac40b274b47a227a486e1565db9.png
         * width : 233
         * height : 253
         */

        private int id;
        private String item;
        private int type;
        private int sort;
        private String title;
        private String content;
        private String image;
        private String width;
        private String height;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
}
