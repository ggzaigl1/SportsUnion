package com.hjy.sports.student.homemodule.corporeity.selftest;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;

import com.fy.baselibrary.base.recyclerv.adapter.RecyclerCommonAdapter;
import com.fy.baselibrary.base.recyclerv.adapter.ViewHolder;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;

import java.util.List;

/**
 * 自我挑战 fragment 列表 adapter
 * Created by fangs on 2018/1/28.
 */
public class SelfChallengeFmAdapter extends RecyclerCommonAdapter<SelfChallengeBean> {

    OnVideoClickListener listener;

    public SelfChallengeFmAdapter(Context context, List<SelfChallengeBean> datas) {
        super(context, R.layout.item_self_challenge, datas);
    }

    @Override
    public void convert(ViewHolder holder, SelfChallengeBean item, int position) {
        holder.setText(R.id.tvSelfItem, item.getTypeName());

        AppCompatImageView imgVideo = holder.getView(R.id.imgVideo);

        if (!TextUtils.isEmpty(item.getVideoPath())){
            ImgLoadUtils.loadCircularBead(mContext, item.getVideoPath(), imgVideo, 8);
        }

        imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) listener.onVideoClick(item, position);
            }
        });
    }



    public void setListener(OnVideoClickListener listener) {
        this.listener = listener;
    }

    /**
     * 选择视频或者拍摄视频
     */
    interface OnVideoClickListener{
        void onVideoClick(SelfChallengeBean item, int position);
    }
}
