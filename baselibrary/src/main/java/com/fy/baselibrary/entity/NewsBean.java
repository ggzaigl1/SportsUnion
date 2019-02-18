package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/3/6.
 */
public class NewsBean {

    /**
     * Data : [{"ID":188594,"Title":"老年人容易便秘，饮食上应该注意些什么","MainImage":"http://5b0988e595225.cdn.sohucs.com/c_fill,w_150,h_100,g_faces,q_70/images/20180308/c2147e5e6b8a4938812fa3748b330a00.jpeg","Body":"随着年龄的增加，老年人的器官功能会出现不同程度的衰退，其中胃肠道功能也会出现一些变化，如消化吸收不良，容易出现便秘等。便秘是指排便次数减少、粪便干硬和（或）排便困难。一般来说，每周排便次数小于3次就可认为是排便次数减少；排便困难的表现有排便费力、排出困难、排便不尽感、排便费时等。便秘会影响老年人的生活质量，同时也威胁着老年人的健康。如果老年人长期便秘，会导致机体内产生的有害物质（如组胺、硫化氢等）不能及时排出，而吸收入血循环，对各组织器官造成不良影响，引起头晕、注意力不集中、记忆力下降、心悸、乏力、烦躁不安、失眠、口臭、皮肤瘙痒等症状；长期便秘还有可能导致老年人发生肠梗阻、肠腔局部缺血坏死、溃疡、穿孔等；便秘的老年人还是心脑血管疾病的高危高发人群，老年人在用力排便的时候，会导致血压升高、心率加快、心肌耗氧量增加，对高血压、冠心病、脑动脉硬化的老年人危害极大，有可能诱发心绞痛、心肌梗死、脑出血等。因此，减少和改善老年人便秘十分重要。（图片来源：网络）便秘的产生除了与老年人的生理变化有关外，还与饮食和运动息息相关。那老年人在日常饮食上应该注意些什么呢？多吃富含膳食纤维的食物。全谷物、豆类、水果、蔬菜及马铃薯是膳食纤维的主要来源，坚果和种子中的含量也很高。增加饮水量。建议老年人每天饮水1500毫升~1700毫升（7~8杯水），由于老年人肾脏功能减弱，体液平衡恢复减慢，口渴感比较迟钝，建议老年人不要在口渴时才饮水，应该养成主动定时饮水的好习惯，尤其是每天清晨饮1杯温开水或蜂蜜水可刺激胃结肠反射，促进肠蠕动。多吃富含益生菌的发酵食物。如酸奶中含有益生菌，有助于维持健康的肠道菌群。适当吃些油脂类食物。油脂具有润肠通便的作用，可以适当吃些芝麻、花生、核桃等含油脂的食物。少吃辛辣等刺激性食物。要养成定时排便的好习惯。增加些运动量，减少久坐。适宜老年人的运动有散步、太极拳、练操等。作者：张娜，营养学博士来源：搜狐健康免责声明：本文注明出处来源及作者，仅代表作者表达内容，不代表康美健康云立场。文章若有侵权，请联系删除。","Category":"","HelpfulCount":0,"ReadingQuantity":202675,"ReleaseTime":"2018-03-08T23:07:02+08:00","LastModifiedTime":"2018-03-09T14:01:21+08:00","URL":"http://www.jkbat.com/App/NewsDetail/188594"}]
     * Total : 2168
     * Status : 0
     * Msg : 获取成功
     * Result : false
     */

    private int Total;
    private int Status;
    private String Msg = "";
    private boolean Result;
    private List<DataBean> Data;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "Total=" + Total +
                ", Status=" + Status +
                ", Msg='" + Msg + '\'' +
                ", Result=" + Result +
                ", Data=" + Data +
                '}';
    }

    public static class DataBean {
        /**
         * ID : 188594
         * Title : 老年人容易便秘，饮食上应该注意些什么
         * MainImage : http://5b0988e595225.cdn.sohucs.com/c_fill,w_150,h_100,g_faces,q_70/images/20180308/c2147e5e6b8a4938812fa3748b330a00.jpeg
         * Body : 随着年龄的增加，老年人的器官功能会出现不同程度的衰退，其中胃肠道功能也会出现一些变化，如消化吸收不良，容易出现便秘等。便秘是指排便次数减少、粪便干硬和（或）排便困难。一般来说，每周排便次数小于3次就可认为是排便次数减少；排便困难的表现有排便费力、排出困难、排便不尽感、排便费时等。便秘会影响老年人的生活质量，同时也威胁着老年人的健康。如果老年人长期便秘，会导致机体内产生的有害物质（如组胺、硫化氢等）不能及时排出，而吸收入血循环，对各组织器官造成不良影响，引起头晕、注意力不集中、记忆力下降、心悸、乏力、烦躁不安、失眠、口臭、皮肤瘙痒等症状；长期便秘还有可能导致老年人发生肠梗阻、肠腔局部缺血坏死、溃疡、穿孔等；便秘的老年人还是心脑血管疾病的高危高发人群，老年人在用力排便的时候，会导致血压升高、心率加快、心肌耗氧量增加，对高血压、冠心病、脑动脉硬化的老年人危害极大，有可能诱发心绞痛、心肌梗死、脑出血等。因此，减少和改善老年人便秘十分重要。（图片来源：网络）便秘的产生除了与老年人的生理变化有关外，还与饮食和运动息息相关。那老年人在日常饮食上应该注意些什么呢？多吃富含膳食纤维的食物。全谷物、豆类、水果、蔬菜及马铃薯是膳食纤维的主要来源，坚果和种子中的含量也很高。增加饮水量。建议老年人每天饮水1500毫升~1700毫升（7~8杯水），由于老年人肾脏功能减弱，体液平衡恢复减慢，口渴感比较迟钝，建议老年人不要在口渴时才饮水，应该养成主动定时饮水的好习惯，尤其是每天清晨饮1杯温开水或蜂蜜水可刺激胃结肠反射，促进肠蠕动。多吃富含益生菌的发酵食物。如酸奶中含有益生菌，有助于维持健康的肠道菌群。适当吃些油脂类食物。油脂具有润肠通便的作用，可以适当吃些芝麻、花生、核桃等含油脂的食物。少吃辛辣等刺激性食物。要养成定时排便的好习惯。增加些运动量，减少久坐。适宜老年人的运动有散步、太极拳、练操等。作者：张娜，营养学博士来源：搜狐健康免责声明：本文注明出处来源及作者，仅代表作者表达内容，不代表康美健康云立场。文章若有侵权，请联系删除。
         * Category :
         * HelpfulCount : 0
         * ReadingQuantity : 202675
         * ReleaseTime : 2018-03-08T23:07:02+08:00
         * LastModifiedTime : 2018-03-09T14:01:21+08:00
         * URL : http://www.jkbat.com/App/NewsDetail/188594
         */

        private int ID;
        private String Title = "";
        private String MainImage = "";
        private String Body = "";
        private String Category = "";
        private int HelpfulCount;
        private int ReadingQuantity;
        private String ReleaseTime = "";
        private String LastModifiedTime = "";
        private String URL = "";

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getMainImage() {
            return MainImage;
        }

        public void setMainImage(String MainImage) {
            this.MainImage = MainImage;
        }

        public String getBody() {
            return Body;
        }

        public void setBody(String Body) {
            this.Body = Body;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public int getHelpfulCount() {
            return HelpfulCount;
        }

        public void setHelpfulCount(int HelpfulCount) {
            this.HelpfulCount = HelpfulCount;
        }

        public int getReadingQuantity() {
            return ReadingQuantity;
        }

        public void setReadingQuantity(int ReadingQuantity) {
            this.ReadingQuantity = ReadingQuantity;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }

        public String getLastModifiedTime() {
            return LastModifiedTime;
        }

        public void setLastModifiedTime(String LastModifiedTime) {
            this.LastModifiedTime = LastModifiedTime;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "ID=" + ID +
                    ", Title='" + Title + '\'' +
                    ", MainImage='" + MainImage + '\'' +
                    ", Body='" + Body + '\'' +
                    ", Category='" + Category + '\'' +
                    ", HelpfulCount=" + HelpfulCount +
                    ", ReadingQuantity=" + ReadingQuantity +
                    ", ReleaseTime='" + ReleaseTime + '\'' +
                    ", LastModifiedTime='" + LastModifiedTime + '\'' +
                    ", URL='" + URL + '\'' +
                    '}';
        }
    }
}
