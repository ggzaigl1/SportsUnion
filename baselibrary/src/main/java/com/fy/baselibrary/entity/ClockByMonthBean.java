package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/2/2.
 */

public class ClockByMonthBean {


    /**
     * msg : 操作成功！
     * code : 0
     * rows : ["2018-02-01","2018-02-15"]
     */

    private List<String> rows;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }
}
