package com.fy.baselibrary.eventbus;

/**
 * Created by PandaQ on 2017/3/10.
 * RxBus 的一些共有常量定义
 */

public class RxConstants {

    /**
     * 作用处1：FragmentFour 校园圈自动刷新
     * 作用处2：MySocialActivity 我发布列表自动刷新
     */
    public final static String STRING_PUBLISH_SUCCEED = "publish_succeed";


    /**
     * 作用处1：FragmentFour 校园圈自动刷新
     * 作用处2：FragmentTwo 体质信息刷新
     */
    public final static String LOGIN_SUCCEED = "login_succeed";


    /**
     * 发送处： MySocialActivity(我发送过的列表)  MySocialDetailActivity(我发送过的列表详情界面)
     * 作用处1：FragmentFour 我发布列表中删除了 本页需要自动刷新下
     * 作用处2：MySocialActivity 我发送过的列表（主要是针对MySocialDetailActivity）这里可以优化通过setResult
     */
    public final static String DELETED_SUCCEED = "deleted_succeed";
}
