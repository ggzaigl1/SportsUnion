package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class NewHeathyFoodBean {

    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":12,"zhongshu":"植物性食物","name":"豆腐皮","yingyangwuzhi":"蛋白质(44.6g/100g)","gongxiao":"豆腐皮中含有丰富的优质蛋白和多种矿物质，可以帮助补充钙质和促进肌肉合成。","image":"/web/e7369bd391d74da88205236ee4fa0e0a.png"},{"id":11,"zhongshu":"肉类","name":"瘦牛肉","yingyangwuzhi":"蛋白质(20.2g/100g)","gongxiao":"牛肉的营养价值居各种肉类的首位，富含蛋白以及铁、锌、维生素B6等元素，很好促进机体蛋白质的合成。","image":"/web/720726dddbe34b58bca697fed75464ea.png"},{"id":10,"zhongshu":"蔬菜","name":"芥菜（雪菜，雪里红）","yingyangwuzhi":"Ca(230mg/100g)","gongxiao":"芥菜除了钙含量很高，还含有丰富的维生素A、B族维生素、维生素C和维生素D。富含膳食纤维，开胃消食。","image":"/web/c3debeeb96314ba2b9bcc0b52fc275ab.png"},{"id":9,"zhongshu":"动物性食品","name":"牛奶","yingyangwuzhi":"Ca(100mg/100ml)","gongxiao":"牛奶是人体钙的最佳来源，而且钙磷比例非常适当，利于钙的吸收。常见的普通牛奶是补钙的最佳奶类。","image":"/web/ef3b0464fa1e492b833768bfdeeeaac5.png"},{"id":8,"zhongshu":"动物性食品","name":"猪肝","yingyangwuzhi":"Fe(22.6mg/100g)","gongxiao":"血色素性铁含量高，吸收好，有效解决贫血问题，促进有氧运动能力提升","image":"/web/40331b73633b476fa01ab24be8550ed6.png"},{"id":7,"zhongshu":"动物性食品","name":"鸭血","yingyangwuzhi":"Fe(30.5mg/100g)","gongxiao":"血色素性铁含量高，吸收好，有效解决贫血问题，促进有氧运动能力提升","image":"/web/e84940cafa2e456dacb3d738cd1b729d.png"},{"id":6,"zhongshu":"水果","name":"柠檬","yingyangwuzhi":"维生素C(22mg/100g)","gongxiao":"清除自由基，抗氧化，提高免疫力","image":"/web/5d1b8367587d424591bbae3bef62c9a6.png"},{"id":5,"zhongshu":"水果","name":"猕猴桃","yingyangwuzhi":"维生素C(62mg/100g)","gongxiao":"清除自由基，抗氧化，提高免疫力","image":"/web/86fa99cb6afa4f4d834914d573cb81f8.png"},{"id":4,"zhongshu":"水果","name":"黑加仑","yingyangwuzhi":"维生素C(181mg/100g)","gongxiao":"清除自由基，抗氧化，提高免疫力","image":"/web/ca72777e85814919ad15101aec4dd9dc.png"},{"id":3,"zhongshu":"水果","name":"猕猴桃","yingyangwuzhi":"维生素C(243mg/100g)","gongxiao":"清除自由基，抗氧化，提高免疫力","image":"/web/b51b99768dcc42a49f540d52b6dbd717.png"}]
     * total : 10
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
         * id : 12
         * zhongshu : 植物性食物
         * name : 豆腐皮
         * yingyangwuzhi : 蛋白质(44.6g/100g)
         * gongxiao : 豆腐皮中含有丰富的优质蛋白和多种矿物质，可以帮助补充钙质和促进肌肉合成。
         * image : /web/e7369bd391d74da88205236ee4fa0e0a.png
         */

        private int id;
        private String zhongshu;
        private String name;
        private String yingyangwuzhi;
        private String gongxiao;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getZhongshu() {
            return zhongshu;
        }

        public void setZhongshu(String zhongshu) {
            this.zhongshu = zhongshu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getYingyangwuzhi() {
            return yingyangwuzhi;
        }

        public void setYingyangwuzhi(String yingyangwuzhi) {
            this.yingyangwuzhi = yingyangwuzhi;
        }

        public String getGongxiao() {
            return gongxiao;
        }

        public void setGongxiao(String gongxiao) {
            this.gongxiao = gongxiao;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
