package com.hjy.sports.student.homemodule.expanded.development;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseActivity;
import com.hjy.sports.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 俱乐部介绍详情
 * Created by Stefan on 2018/3/9.
 */

public class ClubIntroductionActivity extends BaseActivity {

    @BindView(R.id.club_intro)
    TextView club_intro;

    @Override
    protected int getContentView() {
        return 0;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_club_details);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        club_intro.setText(R.string.club_intro);
    }

    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back: //
                finish();
                break;
        }
    }

    /**
     * 重写OnKeyDown 方法 显示动画效果
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void finish() {
        super.finish();
        //关闭activity动画显示
        this.overridePendingTransition(0, R.anim.activity_close);
    }

}
