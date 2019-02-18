package com.hjy.sports.student.main;

import android.view.View;
import android.widget.TextView;

import com.fy.baselibrary.base.CommonDialog;
import com.hjy.sports.R;

/**
 * 提醒用户弹窗
 * Created by fangs on 2018/7/3.
 */
public class RemindDialog extends CommonDialog implements View.OnClickListener{
    Bundler bundler;

    TextView tvDialogTitle;
    TextView tvContent;
    TextView tvDialogCancel;
    TextView tvDialogOk;

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_remind;
    }

    @Override
    protected void baseInit() {
        tvDialogTitle = mRootView.findViewById(R.id.tvDialogTitle);
        tvContent = mRootView.findViewById(R.id.tvContent);
        tvDialogCancel = mRootView.findViewById(R.id.tvDialogCancel);
        tvDialogOk = mRootView.findViewById(R.id.tvDialogOk);

        tvDialogTitle.setText(bundler.titleId);
        tvContent.setText(bundler.content);
        tvDialogCancel.setOnClickListener(this);
        tvDialogOk.setOnClickListener(this);

        setWidthPixels(-1);

        super.baseInit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDialogCancel:
                dismiss();
                break;
            case R.id.tvDialogOk:
                if (null != bundler && null != bundler.getListener())
                    bundler.getListener().onClick();
                dismiss();
                break;
        }
    }


    public void setBundler(Bundler bundler) {
        this.bundler = bundler;
    }

    /**
     * 弹窗 确定 按钮 回调接口
     */
    public interface SelectorListener {
        void onClick();
    }

    public static class Bundler {
        int titleId = -1;
        int content = -1;
        SelectorListener listener;

        public Bundler() {}

        public Bundler setTitleId(int titleId) {
            this.titleId = titleId;
            return this;
        }

        public Bundler setContent(int content) {
            this.content = content;
            return this;
        }

        public SelectorListener getListener() {
            return listener;
        }

        public Bundler setListener(SelectorListener listener) {
            this.listener = listener;
            return this;
        }

        public RemindDialog create() {
            RemindDialog editDialog = new RemindDialog();
            editDialog.setBundler(this);

            return editDialog;
        }
    }
}
