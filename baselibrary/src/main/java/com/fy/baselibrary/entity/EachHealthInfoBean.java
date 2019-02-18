package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2017/12/22.
 */

public class EachHealthInfoBean {


    /**
     * allavg : 142.53
     * schavg : 146.88
     * measurements : [{"id":null,"xuejihao":null,"schoolid":null,"schoolcode":null,"schoolname":null,"nid":null,"ncode":null,"nname":"三年级","bid":null,"bcode":null,"bname":null,"studentid":58142,"studentname":null,"height":137,"weight":30,"feihuoliang":785,"wushimi":8.5,"zuotiweiqianqu":11,"tiaosheng":116,"yangwoqizuo":40,"wushimibawangfan":null,"lidingtiaoyuan":null,"yintixiangshang":null,"yiqianmi":null,"babaimi":null,"celiangdate":null,"heightdf":null,"heightpd":"身高137.0cm","weightdf":null,"weightpd":"体重30.0kg","bmidf":100,"bmipd":"BMI15.98","feihuoliangdf":50,"feihuoliangpd":"肺活量785.0ml","wushimidf":100,"wushimipd":"50米8.5s","zuotiweiqianqudf":76,"zuotiweiqianqupd":"坐位体前屈11.0cm","tiaoshengdf":80,"tiaoshengpd":"跳绳116个","yangwoqizuodf":85,"yangwoqizuopd":"仰卧起坐40个","wushimibawangfandf":null,"wushimibawangfanpd":"","lidingtiaoyuandf":null,"lidingtiaoyuanpd":"","yintixiangshangdf":null,"yintixiangshangpd":"","yiqianmidf":null,"yiqianmipd":"","babaimidf":null,"babaimipd":"","zongtidf":82.2,"zongtipd":"总体82.20分","creater":null,"createdtime":null,"fujiafen":null,"tsfjf":null,"ytxsfjf":null,"ywqzfjf":null,"yqmfjf":null,"bbmfjf":null,"xuehao":null,"carid":null,"sex":null,"minzu":null,"birthday":null,"address":null,"bmi":15.98,"measurementsid":null},{"id":null,"xuejihao":null,"schoolid":null,"schoolcode":null,"schoolname":null,"nid":null,"ncode":null,"nname":"四年级","bid":null,"bcode":null,"bname":null,"studentid":58142,"studentname":null,"height":149,"weight":35.5,"feihuoliang":1439,"wushimi":8.3,"zuotiweiqianqu":7,"tiaosheng":169,"yangwoqizuo":33,"wushimibawangfan":null,"lidingtiaoyuan":null,"yintixiangshang":null,"yiqianmi":null,"babaimi":null,"celiangdate":null,"heightdf":null,"heightpd":"身高149.0cm,比上次长高12.00cm","weightdf":null,"weightpd":"体重35.5kg,比上次增加5.50kg","bmidf":100,"bmipd":"BMI15.99,比上次增加.01","feihuoliangdf":74,"feihuoliangpd":"1439.0ml,比上次增涨654.00ml","wushimidf":100,"wushimipd":"50米8.3s,比上次快.20s","zuotiweiqianqudf":68,"zuotiweiqianqupd":"7.0cm,比上次少4.00cm","tiaoshengdf":100,"tiaoshengpd":"跳绳169个,比上次多53个","yangwoqizuodf":76,"yangwoqizuopd":"33个,比上次少7个","wushimibawangfandf":null,"wushimibawangfanpd":"","lidingtiaoyuandf":null,"lidingtiaoyuanpd":"","yintixiangshangdf":null,"yintixiangshangpd":"","yiqianmidf":null,"yiqianmipd":"","babaimidf":null,"babaimipd":"","zongtidf":97.3,"zongtipd":"总体97.30分,比上次增加15.10分","creater":null,"createdtime":null,"fujiafen":null,"tsfjf":null,"ytxsfjf":null,"ywqzfjf":null,"yqmfjf":null,"bbmfjf":null,"xuehao":null,"carid":null,"sex":null,"minzu":null,"birthday":null,"address":null,"bmi":15.99,"measurementsid":null},{"id":null,"xuejihao":null,"schoolid":null,"schoolcode":null,"schoolname":null,"nid":null,"ncode":null,"nname":"五年级","bid":null,"bcode":null,"bname":null,"studentid":58142,"studentname":null,"height":155,"weight":42,"feihuoliang":2456,"wushimi":8.4,"zuotiweiqianqu":14,"tiaosheng":116,"yangwoqizuo":40,"wushimibawangfan":99,"lidingtiaoyuan":null,"yintixiangshang":null,"yiqianmi":null,"babaimi":null,"celiangdate":null,"heightdf":null,"heightpd":"身高155.0cm,比上次长高6.00cm","weightdf":null,"weightpd":"体重42.0kg,比上次增加6.50kg","bmidf":100,"bmipd":"BMI17.48,比上次增加1.49","feihuoliangdf":100,"feihuoliangpd":"2456.0ml,比上次增涨1017.00ml","wushimidf":95,"wushimipd":"50米8.4s,比上次慢.10s","zuotiweiqianqudf":80,"zuotiweiqianqupd":"14.0cm,比上次多7.00cm","tiaoshengdf":76,"tiaoshengpd":"跳绳116个,比上次少53个","yangwoqizuodf":80,"yangwoqizuopd":"40个,比上次多7个","wushimibawangfandf":100,"wushimibawangfanpd":"往返跑1\u201939\u201d","lidingtiaoyuandf":null,"lidingtiaoyuanpd":"","yintixiangshangdf":null,"yintixiangshangpd":"","yiqianmidf":null,"yiqianmipd":"","babaimidf":null,"babaimipd":"","zongtidf":90.6,"zongtipd":"总体90.60分,比上次减少6.70分","creater":null,"createdtime":null,"fujiafen":null,"tsfjf":null,"ytxsfjf":null,"ywqzfjf":null,"yqmfjf":null,"bbmfjf":null,"xuehao":null,"carid":null,"sex":null,"minzu":null,"birthday":null,"address":null,"bmi":17.48,"measurementsid":null}]
     */

    private double allavg;
    private double schavg;
    private List<MeasurementsBean> measurements;

    public double getAllavg() {
        return allavg;
    }

    public void setAllavg(double allavg) {
        this.allavg = allavg;
    }

    public double getSchavg() {
        return schavg;
    }

    public void setSchavg(double schavg) {
        this.schavg = schavg;
    }

    public List<MeasurementsBean> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsBean> measurements) {
        this.measurements = measurements;
    }

    public static class MeasurementsBean {
        /**
         * id : null
         * xuejihao : null
         * schoolid : null
         * schoolcode : null
         * schoolname : null
         * nid : null
         * ncode : null
         * nname : 三年级
         * bid : null
         * bcode : null
         * bname : null
         * studentid : 58142
         * studentname : null
         * height : 137
         * weight : 30
         * feihuoliang : 785
         * wushimi : 8.5
         * zuotiweiqianqu : 11
         * tiaosheng : 116
         * yangwoqizuo : 40
         * wushimibawangfan : null
         * lidingtiaoyuan : null
         * yintixiangshang : null
         * yiqianmi : null
         * babaimi : null
         * celiangdate : null
         * heightdf : null
         * heightpd : 身高137.0cm
         * weightdf : null
         * weightpd : 体重30.0kg
         * bmidf : 100
         * bmipd : BMI15.98
         * feihuoliangdf : 50
         * feihuoliangpd : 肺活量785.0ml
         * wushimidf : 100
         * wushimipd : 50米8.5s
         * zuotiweiqianqudf : 76
         * zuotiweiqianqupd : 坐位体前屈11.0cm
         * tiaoshengdf : 80
         * tiaoshengpd : 跳绳116个
         * yangwoqizuodf : 85
         * yangwoqizuopd : 仰卧起坐40个
         * wushimibawangfandf : null
         * wushimibawangfanpd :
         * lidingtiaoyuandf : null
         * lidingtiaoyuanpd :
         * yintixiangshangdf : null
         * yintixiangshangpd :
         * yiqianmidf : null
         * yiqianmipd :
         * babaimidf : null
         * babaimipd :
         * zongtidf : 82.2
         * zongtipd : 总体82.20分
         * creater : null
         * createdtime : null
         * fujiafen : null
         * tsfjf : null
         * ytxsfjf : null
         * ywqzfjf : null
         * yqmfjf : null
         * bbmfjf : null
         * xuehao : null
         * carid : null
         * sex : null
         * minzu : null
         * birthday : null
         * address : null
         * bmi : 15.98
         * measurementsid : null
         */

        private Object id;
        private Object xuejihao;
        private Object schoolid;
        private Object schoolcode;
        private Object schoolname;
        private Object nid;
        private Object ncode;
        private String nname;
        private Object bid;
        private Object bcode;
        private Object bname;
        private int studentid;
        private Object studentname;
        private String height;
        private String weight;
        private String feihuoliang;
        private String wushimi;
        private String zuotiweiqianqu;
        private String tiaosheng;
        private String yangwoqizuo;
        private String wushimibawangfan;
        private String lidingtiaoyuan;
        private Object yintixiangshang;
        private Object yiqianmi;
        private Object babaimi;
        private Object celiangdate;
        private Object heightdf;
        private String heightpd;
        private Object weightdf;
        private String weightpd;
        private int bmidf;
        private String bmipd;
        private int feihuoliangdf;
        private String feihuoliangpd;
        private int wushimidf;
        private String wushimipd;
        private int zuotiweiqianqudf;
        private String zuotiweiqianqupd;
        private int tiaoshengdf;
        private String tiaoshengpd;
        private int yangwoqizuodf;
        private String yangwoqizuopd;
        private Object wushimibawangfandf;
        private String wushimibawangfanpd;
        private Object lidingtiaoyuandf;
        private String lidingtiaoyuanpd;
        private Object yintixiangshangdf;
        private String yintixiangshangpd;
        private Object yiqianmidf;
        private String yiqianmipd;
        private Object babaimidf;
        private String babaimipd;
        private String zongtidf;
        private String zongtipd;
        private Object creater;
        private Object createdtime;
        private Object fujiafen;
        private Object tsfjf;
        private Object ytxsfjf;
        private Object ywqzfjf;
        private Object yqmfjf;
        private Object bbmfjf;
        private Object xuehao;
        private Object carid;
        private Object sex;
        private Object minzu;
        private Object birthday;
        private Object address;
        private String bmi;
        private Object measurementsid;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getXuejihao() {
            return xuejihao;
        }

        public void setXuejihao(Object xuejihao) {
            this.xuejihao = xuejihao;
        }

        public Object getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(Object schoolid) {
            this.schoolid = schoolid;
        }

        public Object getSchoolcode() {
            return schoolcode;
        }

        public void setSchoolcode(Object schoolcode) {
            this.schoolcode = schoolcode;
        }

        public Object getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(Object schoolname) {
            this.schoolname = schoolname;
        }

        public Object getNid() {
            return nid;
        }

        public void setNid(Object nid) {
            this.nid = nid;
        }

        public Object getNcode() {
            return ncode;
        }

        public void setNcode(Object ncode) {
            this.ncode = ncode;
        }

        public String getNname() {
            return nname;
        }

        public void setNname(String nname) {
            this.nname = nname;
        }

        public Object getBid() {
            return bid;
        }

        public void setBid(Object bid) {
            this.bid = bid;
        }

        public Object getBcode() {
            return bcode;
        }

        public void setBcode(Object bcode) {
            this.bcode = bcode;
        }

        public Object getBname() {
            return bname;
        }

        public void setBname(Object bname) {
            this.bname = bname;
        }

        public int getStudentid() {
            return studentid;
        }

        public void setStudentid(int studentid) {
            this.studentid = studentid;
        }

        public Object getStudentname() {
            return studentname;
        }

        public void setStudentname(Object studentname) {
            this.studentname = studentname;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getFeihuoliang() {
            return feihuoliang;
        }

        public void setFeihuoliang(String feihuoliang) {
            this.feihuoliang = feihuoliang;
        }

        public String getWushimi() {
            return wushimi;
        }

        public void setWushimi(String wushimi) {
            this.wushimi = wushimi;
        }

        public String getZuotiweiqianqu() {
            return zuotiweiqianqu;
        }

        public void setZuotiweiqianqu(String zuotiweiqianqu) {
            this.zuotiweiqianqu = zuotiweiqianqu;
        }

        public String getTiaosheng() {
            return tiaosheng;
        }

        public void setTiaosheng(String tiaosheng) {
            this.tiaosheng = tiaosheng;
        }

        public String getYangwoqizuo() {
            return yangwoqizuo;
        }

        public void setYangwoqizuo(String yangwoqizuo) {
            this.yangwoqizuo = yangwoqizuo;
        }

        public String getWushimibawangfan() {
            return wushimibawangfan;
        }

        public void setWushimibawangfan(String wushimibawangfan) {
            this.wushimibawangfan = wushimibawangfan;
        }

        public String getLidingtiaoyuan() {
            return lidingtiaoyuan;
        }

        public void setLidingtiaoyuan(String lidingtiaoyuan) {
            this.lidingtiaoyuan = lidingtiaoyuan;
        }

        public Object getYintixiangshang() {
            return yintixiangshang;
        }

        public void setYintixiangshang(Object yintixiangshang) {
            this.yintixiangshang = yintixiangshang;
        }

        public Object getYiqianmi() {
            return yiqianmi;
        }

        public void setYiqianmi(Object yiqianmi) {
            this.yiqianmi = yiqianmi;
        }

        public Object getBabaimi() {
            return babaimi;
        }

        public void setBabaimi(Object babaimi) {
            this.babaimi = babaimi;
        }

        public Object getCeliangdate() {
            return celiangdate;
        }

        public void setCeliangdate(Object celiangdate) {
            this.celiangdate = celiangdate;
        }

        public Object getHeightdf() {
            return heightdf;
        }

        public void setHeightdf(Object heightdf) {
            this.heightdf = heightdf;
        }

        public String getHeightpd() {
            return heightpd;
        }

        public void setHeightpd(String heightpd) {
            this.heightpd = heightpd;
        }

        public Object getWeightdf() {
            return weightdf;
        }

        public void setWeightdf(Object weightdf) {
            this.weightdf = weightdf;
        }

        public String getWeightpd() {
            return weightpd;
        }

        public void setWeightpd(String weightpd) {
            this.weightpd = weightpd;
        }

        public int getBmidf() {
            return bmidf;
        }

        public void setBmidf(int bmidf) {
            this.bmidf = bmidf;
        }

        public String getBmipd() {
            return bmipd;
        }

        public void setBmipd(String bmipd) {
            this.bmipd = bmipd;
        }

        public int getFeihuoliangdf() {
            return feihuoliangdf;
        }

        public void setFeihuoliangdf(int feihuoliangdf) {
            this.feihuoliangdf = feihuoliangdf;
        }

        public String getFeihuoliangpd() {
            return feihuoliangpd;
        }

        public void setFeihuoliangpd(String feihuoliangpd) {
            this.feihuoliangpd = feihuoliangpd;
        }

        public int getWushimidf() {
            return wushimidf;
        }

        public void setWushimidf(int wushimidf) {
            this.wushimidf = wushimidf;
        }

        public String getWushimipd() {
            return wushimipd;
        }

        public void setWushimipd(String wushimipd) {
            this.wushimipd = wushimipd;
        }

        public int getZuotiweiqianqudf() {
            return zuotiweiqianqudf;
        }

        public void setZuotiweiqianqudf(int zuotiweiqianqudf) {
            this.zuotiweiqianqudf = zuotiweiqianqudf;
        }

        public String getZuotiweiqianqupd() {
            return zuotiweiqianqupd;
        }

        public void setZuotiweiqianqupd(String zuotiweiqianqupd) {
            this.zuotiweiqianqupd = zuotiweiqianqupd;
        }

        public int getTiaoshengdf() {
            return tiaoshengdf;
        }

        public void setTiaoshengdf(int tiaoshengdf) {
            this.tiaoshengdf = tiaoshengdf;
        }

        public String getTiaoshengpd() {
            return tiaoshengpd;
        }

        public void setTiaoshengpd(String tiaoshengpd) {
            this.tiaoshengpd = tiaoshengpd;
        }

        public int getYangwoqizuodf() {
            return yangwoqizuodf;
        }

        public void setYangwoqizuodf(int yangwoqizuodf) {
            this.yangwoqizuodf = yangwoqizuodf;
        }

        public String getYangwoqizuopd() {
            return yangwoqizuopd;
        }

        public void setYangwoqizuopd(String yangwoqizuopd) {
            this.yangwoqizuopd = yangwoqizuopd;
        }

        public Object getWushimibawangfandf() {
            return wushimibawangfandf;
        }

        public void setWushimibawangfandf(Object wushimibawangfandf) {
            this.wushimibawangfandf = wushimibawangfandf;
        }

        public String getWushimibawangfanpd() {
            return wushimibawangfanpd;
        }

        public void setWushimibawangfanpd(String wushimibawangfanpd) {
            this.wushimibawangfanpd = wushimibawangfanpd;
        }

        public Object getLidingtiaoyuandf() {
            return lidingtiaoyuandf;
        }

        public void setLidingtiaoyuandf(Object lidingtiaoyuandf) {
            this.lidingtiaoyuandf = lidingtiaoyuandf;
        }

        public String getLidingtiaoyuanpd() {
            return lidingtiaoyuanpd;
        }

        public void setLidingtiaoyuanpd(String lidingtiaoyuanpd) {
            this.lidingtiaoyuanpd = lidingtiaoyuanpd;
        }

        public Object getYintixiangshangdf() {
            return yintixiangshangdf;
        }

        public void setYintixiangshangdf(Object yintixiangshangdf) {
            this.yintixiangshangdf = yintixiangshangdf;
        }

        public String getYintixiangshangpd() {
            return yintixiangshangpd;
        }

        public void setYintixiangshangpd(String yintixiangshangpd) {
            this.yintixiangshangpd = yintixiangshangpd;
        }

        public Object getYiqianmidf() {
            return yiqianmidf;
        }

        public void setYiqianmidf(Object yiqianmidf) {
            this.yiqianmidf = yiqianmidf;
        }

        public String getYiqianmipd() {
            return yiqianmipd;
        }

        public void setYiqianmipd(String yiqianmipd) {
            this.yiqianmipd = yiqianmipd;
        }

        public Object getBabaimidf() {
            return babaimidf;
        }

        public void setBabaimidf(Object babaimidf) {
            this.babaimidf = babaimidf;
        }

        public String getBabaimipd() {
            return babaimipd;
        }

        public void setBabaimipd(String babaimipd) {
            this.babaimipd = babaimipd;
        }

        public String getZongtidf() {
            return zongtidf;
        }

        public void setZongtidf(String zongtidf) {
            this.zongtidf = zongtidf;
        }

        public String getZongtipd() {
            return zongtipd;
        }

        public void setZongtipd(String zongtipd) {
            this.zongtipd = zongtipd;
        }

        public Object getCreater() {
            return creater;
        }

        public void setCreater(Object creater) {
            this.creater = creater;
        }

        public Object getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(Object createdtime) {
            this.createdtime = createdtime;
        }

        public Object getFujiafen() {
            return fujiafen;
        }

        public void setFujiafen(Object fujiafen) {
            this.fujiafen = fujiafen;
        }

        public Object getTsfjf() {
            return tsfjf;
        }

        public void setTsfjf(Object tsfjf) {
            this.tsfjf = tsfjf;
        }

        public Object getYtxsfjf() {
            return ytxsfjf;
        }

        public void setYtxsfjf(Object ytxsfjf) {
            this.ytxsfjf = ytxsfjf;
        }

        public Object getYwqzfjf() {
            return ywqzfjf;
        }

        public void setYwqzfjf(Object ywqzfjf) {
            this.ywqzfjf = ywqzfjf;
        }

        public Object getYqmfjf() {
            return yqmfjf;
        }

        public void setYqmfjf(Object yqmfjf) {
            this.yqmfjf = yqmfjf;
        }

        public Object getBbmfjf() {
            return bbmfjf;
        }

        public void setBbmfjf(Object bbmfjf) {
            this.bbmfjf = bbmfjf;
        }

        public Object getXuehao() {
            return xuehao;
        }

        public void setXuehao(Object xuehao) {
            this.xuehao = xuehao;
        }

        public Object getCarid() {
            return carid;
        }

        public void setCarid(Object carid) {
            this.carid = carid;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getMinzu() {
            return minzu;
        }

        public void setMinzu(Object minzu) {
            this.minzu = minzu;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getBmi() {
            return bmi;
        }

        public void setBmi(String bmi) {
            this.bmi = bmi;
        }

        public Object getMeasurementsid() {
            return measurementsid;
        }

        public void setMeasurementsid(Object measurementsid) {
            this.measurementsid = measurementsid;
        }
    }
}
