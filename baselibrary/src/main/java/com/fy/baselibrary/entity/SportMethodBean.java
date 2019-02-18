package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/2/2.
 */

public class SportMethodBean {


    /**
     * offset : 0
     * limit : 2147483647
     * pageNo : 1
     * pageSize : 10
     * rows : [{"id":1,"sportsName":"单腿前屈","sportsMethod":"练习者直角坐，一条腿自然弯曲，另一条腿保持伸直，身体前屈双手抓住脚，身体尽量靠近伸直的腿，可以按口令身体有节奏地向下振动。交换腿练习，方法相同。两人配合练习时，练习者面对面直角坐，将伸直腿的脚掌对着脚掌，双手互握，互相拉锯式进行练习。拓展练习的方法是一条腿伸直，另一条腿屈腿，并使腿部外侧贴地，或内侧贴地","competitionMode":"以身体贴紧伸直腿的程度来评判名次","securityEssentials":"要保持一腿伸直，练习过程中要缓慢、持续用力，避免用爆发力，防止拉伤韧带。双方配合练习时避免使用爆发力，以免拉伤对方韧带","image":null,"score":2},{"id":2,"sportsName":"单腿前屈拉锯练习（双人）","sportsMethod":"练习者直角坐，双腿保持伸直，两臂交叉，右臂在上，两手抓住两脚外侧，体前屈同时身体向右扭转，头从两臂间向上看，尽量使背部贴腿，胸部朝上。交换方向，动作相同。可以在他人帮助下练习，帮助者在练习者上方用手扶住练习者肩部，帮助练习者充分扭转","competitionMode":"在膝盖后侧贴地的基础上，以身体扭转的程度来评判名次，或者以扭转坚持的时间长短来评定等级。","securityEssentials":"帮助者要根据练习者的实际情况，施加适当的外力，以免练习者受伤。","image":null,"score":2},{"id":3,"sportsName":"小鸵鸟觅食","sportsMethod":"两脚前后自然站立，重心落在两腿之间，体后屈臂，双手合掌，手指额尽量向上，抬头挺胸，体前屈，前额尽量靠近前方小腿，换另一条腿在前，动作相同。前后站立，其他动作相同。拓展练习的方法是：两脚脚尖向前，前后站立，其他动作相同。","competitionMode":"以前额触碰脚踝、小腿和膝盖等不同的部位来评判优良。","securityEssentials":"要在平稳的和不滑脚的地面上进行练习，身体前屈时要慢速，注意保持平衡，以免摔倒。","image":null,"score":2}]
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

    public static class RowsBean {
        /**
         * id : 1
         * sportsName : 单腿前屈
         * sportsMethod : 练习者直角坐，一条腿自然弯曲，另一条腿保持伸直，身体前屈双手抓住脚，身体尽量靠近伸直的腿，可以按口令身体有节奏地向下振动。交换腿练习，方法相同。两人配合练习时，练习者面对面直角坐，将伸直腿的脚掌对着脚掌，双手互握，互相拉锯式进行练习。拓展练习的方法是一条腿伸直，另一条腿屈腿，并使腿部外侧贴地，或内侧贴地
         * competitionMode : 以身体贴紧伸直腿的程度来评判名次
         * securityEssentials : 要保持一腿伸直，练习过程中要缓慢、持续用力，避免用爆发力，防止拉伤韧带。双方配合练习时避免使用爆发力，以免拉伤对方韧带
         * image : null
         * score : 2
         */

        private int id;
        private String sportsName;
        private String sportsMethod;
        private String competitionMode;
        private String securityEssentials;
        private String image;
        private int score;

        //加上几个参数  isDaka  练习方法id
        private boolean isDaka;
        private long sportsMethodId;

        public boolean isDaka() {
            return isDaka;
        }

        public void setDaka(boolean daka) {
            isDaka = daka;
        }

        public long getSportsMethodId() {
            return sportsMethodId;
        }

        public void setSportsMethodId(long sportsMethodId) {
            this.sportsMethodId = sportsMethodId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSportsName() {
            return sportsName;
        }

        public void setSportsName(String sportsName) {
            this.sportsName = sportsName;
        }

        public String getSportsMethod() {
            return sportsMethod;
        }

        public void setSportsMethod(String sportsMethod) {
            this.sportsMethod = sportsMethod;
        }

        public String getCompetitionMode() {
            return competitionMode;
        }

        public void setCompetitionMode(String competitionMode) {
            this.competitionMode = competitionMode;
        }

        public String getSecurityEssentials() {
            return securityEssentials;
        }

        public void setSecurityEssentials(String securityEssentials) {
            this.securityEssentials = securityEssentials;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
