package com.padcmyanmar.padc7.mmnews;

import android.app.Application;

import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl;

public class MMNewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserModelImpl.initUserModel(getApplicationContext());
    }
}
