package com.padcmyanmar.padc7.mmnews.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.persistence.dao.LoginUserDao;

@Database(entities = {LoginUserVO.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase INSTANCE;

    public abstract LoginUserDao loginUserDao();

    public static NewsDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, NewsDatabase.class, "NewsDabase.DB")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
