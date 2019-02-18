package com.hjy.sports.util;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.fy.baselibrary.utils.JumpUtils;
import com.hjy.sports.president.main.PresidentMainActivity;
import com.hjy.sports.student.main.StudentMainActivity;

/**
 * Created by fangs on 2018/4/8.
 */
public class ActJump {

    /**
     * 根据角色 跳转到不同的 界面
     *
     * @param act
     * @param role
     */
    public static void jumpRole(AppCompatActivity act, String role) {
        if (!TextUtils.isEmpty(role) && role.equals("school")) {
            JumpUtils.jump(act, PresidentMainActivity.class, null);
        } else {
            JumpUtils.jump(act, StudentMainActivity.class, null);
        }
    }
}
