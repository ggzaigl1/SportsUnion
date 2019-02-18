package com.hjy.sports.student.main.fragment;

import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.baselibrary.base.BaseFragment;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.retrofit.ApiService;
import com.fy.baselibrary.retrofit.NetCallBack;
import com.fy.baselibrary.retrofit.RxHelper;
import com.fy.baselibrary.retrofit.RxNetCache;
import com.fy.baselibrary.retrofit.dialog.IProgressDialog;
import com.fy.baselibrary.utils.ConstantUtils;
import com.fy.baselibrary.utils.JumpUtils;
import com.fy.baselibrary.utils.ResourceUtils;
import com.fy.baselibrary.utils.TintUtils;
import com.fy.baselibrary.utils.imgload.ImgLoadUtils;
import com.hjy.sports.R;
import com.hjy.sports.student.login.LoginActivity;
import com.hjy.sports.student.main.RemindDialog;
import com.hjy.sports.student.modify.info.ModifyInfoActivity;
import com.hjy.sports.student.modify.info.ModifyPassActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 * Created by fangs on 2017/12/12.
 */
public class FragmentFive extends BaseFragment {

    @BindView(R.id.imgHead)
    ImageView imgHead;
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;

    @BindView(R.id.tvSchoolNum)
    TextView tvSchoolNum;//学籍
    @BindView(R.id.tvBirthday)
    TextView tvBirthday;//生日
    @BindView(R.id.tvGrade)
    TextView tvGrade;//年级
    @BindView(R.id.tvClassGrade)
    TextView tvClassGrade;//班级
    @BindView(R.id.tvSchool)
    TextView tvSchool;//学校
    @BindView(R.id.tvParentPhone)
    TextView tvParentPhone;//家长电话

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main_five;
    }

    @Override
    protected void baseInit() {

    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        LoginBean.StudentBean studentInfo = (LoginBean.StudentBean) mCache.getAsObject(ConstantUtils.student);
        if (null != studentInfo) {
//            根据sex设置不同图标
            int icSex = studentInfo.getSex().equals("1") ? R.drawable.vector_sex_man : R.drawable.vector_sex_woman;
            TintUtils.setCompoundDrawable(tvName, icSex, 2);
            tvName.setText(studentInfo.getName());
            ImgLoadUtils.loadCircleImg(ApiService.IMG_BASE_URL + studentInfo.getTouxiangurl(), imgHead);

            tvHeight.setText(getSpann(R.string.height, studentInfo.getHeight() + ""));
            tvWeight.setText(getSpann(R.string.weight, studentInfo.getWeight() + ""));
            tvAge.setText(getSpann(R.string.age, studentInfo.getAge() + ""));

            tvSchoolNum.setText(studentInfo.getXuejihao());
            tvBirthday.setText(studentInfo.getBirthday());
            tvGrade.setText(studentInfo.getNname());
            tvClassGrade.setText(studentInfo.getBname());
            tvSchool.setText(studentInfo.getSchoolname());
            tvParentPhone.setText(studentInfo.getJiazhangphone());
        }
    }

    /**
     * 使用 Spannable 动态设置 textview 显示内容
     *
     * @param id
     * @param replaceStr
     * @return
     */
    private Spannable getSpann(int id, String replaceStr) {
        Spannable sp = new SpannableString(ResourceUtils.getText(id, replaceStr));
        sp.setSpan(new AbsoluteSizeSpan(21, true), 0, replaceStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.login)), 0, replaceStr.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(14, true), replaceStr.length(), sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return sp;
    }

    @OnClick({R.id.tvEdit, R.id.llModifyPass, R.id.llExitLogin})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvEdit:
                JumpUtils.jump(mContext, ModifyInfoActivity.class, null);
                break;
            case R.id.llModifyPass://修改密码
                if (!TextUtils.isEmpty(tvParentPhone.getText().toString())){
                    JumpUtils.jump(mContext, ModifyPassActivity.class, null);
                } else {
                    new RemindDialog.Bundler()
                            .setTitleId(R.string.dialog_title)
                            .setContent(R.string.dialog_remind_msg)
                            .setListener(() -> JumpUtils.jump(mContext, ModifyInfoActivity.class, null))
                            .create()
                            .show(getFragmentManager());
                }
                break;
            case R.id.llExitLogin://退出登录
                loginOut();
                break;
        }
    }

    //退出登录
    private void loginOut() {
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.exit_loading);
        Map<String, Object> param = new HashMap<>();
        param.put("token", ConstantUtils.token);
        new RxNetCache.Builder()
                .create()
                .request(mConnService.loginOut(param).compose(RxHelper.handleResult()))
                .subscribe(new NetCallBack<Object>(progressDialog) {
                    @Override
                    public void onSuccess(Object bean) {
                        //清空 token
                        ConstantUtils.token = "";
                        mCache.put("token", "", 1);
//                                ConstantUtils.studentID = 0;
//                                SpfUtils.getSpfSaveInt("studentId");
//                                mCache.clear();
                        JumpUtils.jump(mContext, LoginActivity.class, null);
                    }

                    @Override
                    public void updataLayout(int flag) {
                    }
                });
    }

}
