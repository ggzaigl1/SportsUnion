package com.fy.baselibrary.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by QKun on 2017/12/26.
 */

public class MealsBean {

    /**
     * msg : 操作成功！
     * code : 0
     * rows : {"offset":0,"limit":2147483647,"pageNo":1,"pageSize":10,"rows":[{"id":7,"name":"涨体能菜","tuijiandu":"3","jijie":"4","zhendui":"身体机能","cailiao":"aa","miaoshu":"bb","yingyangzhi":11,"imageurl":"/static/upload/web/e8131056647d4c4c95ea236f21d5ea0d.jpeg"},{"id":5,"name":"涨速度菜","tuijiandu":"1","jijie":"4","zhendui":"速度素质","cailiao":"1","miaoshu":"2","yingyangzhi":3,"imageurl":""},{"id":3,"name":"涨机能菜","tuijiandu":"1","jijie":"4","zhendui":"身体机能","cailiao":"11","miaoshu":"22","yingyangzhi":33,"imageurl":""}],"total":3,"totalPages":1,"first":1}
     */


    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":7,"name":"涨体能菜","tuijiandu":"3","jijie":"4","zhendui":"身体机能","cailiao":"aa","miaoshu":"bb","yingyangzhi":11,"imageurl":"/static/upload/web/e8131056647d4c4c95ea236f21d5ea0d.jpeg"},{"id":5,"name":"涨速度菜","tuijiandu":"1","jijie":"4","zhendui":"速度素质","cailiao":"1","miaoshu":"2","yingyangzhi":3,"imageurl":""},{"id":3,"name":"涨机能菜","tuijiandu":"1","jijie":"4","zhendui":"身体机能","cailiao":"11","miaoshu":"22","yingyangzhi":33,"imageurl":""}]
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



    public static class RowsBean implements MultiItemEntity{

        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        /**
         * id : 7
         * name : 涨体能菜
         * tuijiandu : 3
         * jijie : 4
         * zhendui : 身体机能
         * cailiao : aa
         * miaoshu : bb
         * yingyangzhi : 11
         * imageurl : /static/upload/web/e8131056647d4c4c95ea236f21d5ea0d.jpeg
         */
        private int itemType;
        private int id;
        private String name;
        private String tuijiandu;
        private String jijie;
        private String zhendui;
        private String cailiao;
        private String miaoshu;
        private int yingyangzhi;
        private String imageurl;

        public RowsBean(int itemType, int id, String name, String tuijiandu, String cailiao, String miaoshu, int yingyangzhi, String imageurl) {
            this.itemType = itemType;
            this.id = id;
            this.name = name;
            this.tuijiandu = tuijiandu;
            this.cailiao = cailiao;
            this.miaoshu = miaoshu;
            this.yingyangzhi = yingyangzhi;
            this.imageurl = imageurl;
        }

        public RowsBean(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

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

        public String getTuijiandu() {
            return tuijiandu;
        }

        public void setTuijiandu(String tuijiandu) {
            this.tuijiandu = tuijiandu;
        }

        public String getJijie() {
            return jijie;
        }

        public void setJijie(String jijie) {
            this.jijie = jijie;
        }

        public String getZhendui() {
            return zhendui;
        }

        public void setZhendui(String zhendui) {
            this.zhendui = zhendui;
        }

        public String getCailiao() {
            return cailiao;
        }

        public void setCailiao(String cailiao) {
            this.cailiao = cailiao;
        }

        public String getMiaoshu() {
            return miaoshu;
        }

        public void setMiaoshu(String miaoshu) {
            this.miaoshu = miaoshu;
        }

        public int getYingyangzhi() {
            return yingyangzhi;
        }

        public void setYingyangzhi(int yingyangzhi) {
            this.yingyangzhi = yingyangzhi;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }

}
