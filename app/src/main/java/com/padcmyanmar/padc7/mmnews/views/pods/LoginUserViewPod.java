package com.padcmyanmar.padc7.mmnews.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.ViewPodDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginUserViewPod extends RelativeLayout
        implements ViewPodDelegate<LoginUserViewPod.LoginUserViewPodDelegate> {

    @BindView(R.id.iv_login_user_bg)
    ImageView ivLoginUserBg;

    @BindView(R.id.iv_login_user)
    ImageView ivLoginUser;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone_no)
    TextView tvPhoneNo;

    private LoginUserViewPodDelegate mDelegate;

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

    @Override
    public void setDelegate(LoginUserViewPodDelegate delegate) {
        mDelegate = delegate;
    }

    @OnClick(R.id.btn_logout)
    public void onTapLogout(View view) {
        if(mDelegate == null) {
            throw new RuntimeException("Need to set the delegate for LoginUserViewPod first.");
        }
        mDelegate.onTapLogout();
    }

    public interface LoginUserViewPodDelegate {
        void onTapLogout();
    }
}
