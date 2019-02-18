package com.hjy.sports.student.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by QKun on 2017/12/15.
 */

public class MySection extends SectionEntity<ImageBean> {


    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(ImageBean imageBean) {
        super(imageBean);
    }
}
