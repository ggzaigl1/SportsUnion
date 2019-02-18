package com.hjy.sports.student.socialmodule;

import android.view.Gravity;
import android.widget.TextView;

import com.fy.baselibrary.base.CommonDialog;
import com.fy.baselibrary.utils.ScreenUtils;
import com.hjy.sports.R;

/**
 * 删除 提示dialog
 * Created by fangs on 2017/12/29.
 */
public class DeleteTipsDialog extends CommonDialog {

    OnDeleteListener listener;

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_delete_tips;
    }

    @Override
    protected void baseInit() {

        TextView tvDialogDelete = mRootView.findViewById(R.id.tvDialogDelete);
        tvDialogDelete.setOnClickListener(v -> {
            if (null != listener) listener.OnClick();
            dismiss();
        });

        TextView tvDialogCancel = mRootView.findViewById(R.id.tvDialogCancel);
        tvDialogCancel.setOnClickListener(v -> dismiss());

        setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        setAnim(R.style.AnimUp);
        setWidthPixels(ScreenUtils.getScreenWidth(this.getActivity()) - 80);

        super.baseInit();
    }

    public void setListener(OnDeleteListener listener) {
        this.listener = listener;
    }

    interface OnDeleteListener {
        void OnClick();
    }
}
