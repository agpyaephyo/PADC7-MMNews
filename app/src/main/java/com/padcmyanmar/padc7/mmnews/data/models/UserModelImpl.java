package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.util.Log;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;

public class UserModelImpl extends BaseModel implements UserModel {

    private static UserModel instance;

    private UserModelImpl(Context context) {
        super(context);
    }

    public static void initUserModel(Context context) {
        instance = new UserModelImpl(context);
    }

    public static UserModel getInstance() {
        if (instance == null) {
            throw new RuntimeException("UserModel should have been initialized before using it.");
        }
        return instance;
    }

    @Override
    public void login(String emailOrPassword, String password, final LoginDelegate loginDelegate) {
        mDataAgent.login(emailOrPassword, password, new LoginDelegate() {
            @Override
            public void onSuccess(LoginUserVO loginUser) {
                long userId = mNewsDB.loginUserDao().inserLoginUser(loginUser);
                Log.d("", "userId : " + userId);
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
        LoginUserVO loginUser = mNewsDB.loginUserDao().getLoginUser();
        return loginUser;
    }

    @Override
    public boolean isUserLogin() {
        return mNewsDB.loginUserDao().getLoginUser() != null;
    }

    @Override
    public void onUserLogout() {
        mNewsDB.loginUserDao().deleteLoginUser();
    }
}
