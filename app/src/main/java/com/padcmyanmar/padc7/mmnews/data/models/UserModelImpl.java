package com.padcmyanmar.padc7.mmnews.data.models;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;
import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA;

public class UserModelImpl extends BaseModel implements UserModel {

    private static UserModel instance;

    private NewsDataAgent mDataAgent;

    private LoginUserVO mLoginUser;

    private UserModelImpl() {
        mDataAgent = RetrofitDA.getInstance();
    }

    public static UserModel getInstance() {
        if (instance == null) {
            instance = new UserModelImpl();
        }
        return instance;
    }

    @Override
    public void login(String emailOrPassword, String password, final LoginDelegate loginDelegate) {
        mDataAgent.login(emailOrPassword, password, new LoginDelegate() {
            @Override
            public void onSuccess(LoginUserVO loginUser) {
                mLoginUser = loginUser;
                loginDelegate.onSuccess(loginUser);
            }

            @Override
            public void onFail(String msg) {
                loginDelegate.onFail(msg);
            }
        });
    }

    @Override
    public LoginUserVO getLoginUser() {
        return mLoginUser;
    }
}
