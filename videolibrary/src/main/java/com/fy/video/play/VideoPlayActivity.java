package com.fy.video.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.video.R;


/**
 * 视频播放 Activity
 * Created by fangs on 2018/1/29.
 */
public class VideoPlayActivity extends BaseActivity{

    NiceVideoPlayer mNiceVideoPlayer;

    String videoUrl;//视频地址

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.act_video_play);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        videoUrl = bundle.getString("VideoPath", "");

        mNiceVideoPlayer = findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
        mNiceVideoPlayer.setUp(videoUrl, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("");
//        controller.setLenght(117000);
        mNiceVideoPlayer.setController(controller);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return true;
        return super.onKeyDown(keyCode, event);
    }
}
