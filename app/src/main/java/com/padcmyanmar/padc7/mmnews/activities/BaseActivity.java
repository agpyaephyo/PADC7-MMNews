package com.padcmyanmar.padc7.mmnews.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.padcmyanmar.padc7.mmnews.data.models.NewsModel;
import com.padcmyanmar.padc7.mmnews.data.models.NewsModelImpl;
import com.padcmyanmar.padc7.mmnews.data.models.UserModel;
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl;

public abstract class BaseActivity extends AppCompatActivity {

    protected NewsModel mNewsModel;
    protected UserModel mUserModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsModel = NewsModelImpl.getInstance();
        mUserModel = UserModelImpl.getInstance();
    }
}
