package com.padcmyanmar.padc7.mmnews.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;

@Dao
public interface LoginUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long inserLoginUser(LoginUserVO loginUser);

    @Query("SELECT * FROM login_user")
    LoginUserVO getLoginUser();

    @Query("DELETE FROM login_user")
    void deleteLoginUser();
}
