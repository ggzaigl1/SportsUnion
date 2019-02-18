package com.hjy.sports.student.homemodule.expanded.ornamental;

import com.fy.baselibrary.entity.CourseDetails;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2018/3/14 0014.
 */

public class OrListBean implements Serializable {

    List<CourseDetails.DataBean.GroupsBean.ActionsBean> data;

    public List<CourseDetails.DataBean.GroupsBean.ActionsBean> getData() {
        return data;
    }

    public void setData(List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        this.data = data;
    }

    public OrListBean(List<CourseDetails.DataBean.GroupsBean.ActionsBean> data) {
        this.data = data;
    }
}
