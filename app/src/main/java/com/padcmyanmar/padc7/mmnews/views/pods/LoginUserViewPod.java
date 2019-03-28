package com.padcmyanmar.padc7.mmnews.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginUserViewPod extends RelativeLayout {

    @BindView(R.id.iv_login_user_bg)
    ImageView ivLoginUserBg;

    @BindView(R.id.iv_login_user)
    ImageView ivLoginUser;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone_no)
    TextView tvPhoneNo;

    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(LoginUserVO loginUser) {
        tvName.setText(loginUser.getName());
        tvPhoneNo.setText(loginUser.getPhoneNo());


    }
}
