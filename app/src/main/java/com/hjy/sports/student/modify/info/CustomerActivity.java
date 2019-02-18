package com.hjy.sports.student.modify.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fy.baselibrary.base.BaseActivity;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.T;
import com.fy.baselibrary.utils.Validator;
import com.hjy.sports.R;

import butterknife.BindView;

/**
 * Created by 初夏小溪 on 2018/3/20 0020.
 *  修改手機號碼
 */
public class CustomerActivity extends BaseActivity {

    @BindView(R.id.edit_context)
    EditText mEditContext;
    @BindView(R.id.bt_Update)
    Button mBtUpdate;

    @Override
    protected int getContentView() {
        return R.layout.activity_cutomer;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvMenu.setVisibility(View.GONE);
        tvTitle.setText("修改手机号码");
        String phone = getIntent().getExtras().getString("phone");
        if (!TextUtils.isEmpty(phone)){
            mEditContext.setText(phone);
        }

        mBtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Context = mEditContext.getText().toString();
                if (TextUtils.isEmpty(Context) || !Validator.isMobile(Context)) {
                    T.showShort("请输入正确的手机号");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("context", Context);
                    JumpUtils.jumpResult(CustomerActivity.this, bundle);
                }
            }
        });
    }
}
