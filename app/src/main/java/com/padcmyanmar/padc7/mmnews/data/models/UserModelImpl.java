package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.util.Log;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;
import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA;
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase;

public class UserModelImpl extends BaseModel implements UserModel {

    private static UserModel instance;

    private NewsDataAgent mDataAgent;

    private NewsDatabase mNewsDB;

    private UserModelImpl(Context context) {
        mDataAgent = RetrofitDA.getInstance();
        mNewsDB = NewsDatabase.getDatabase(context);
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
}
