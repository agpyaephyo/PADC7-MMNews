package com.padcmyanmar.padc7.mmnews.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.persistence.dao.CommentDao;
import com.padcmyanmar.padc7.mmnews.persistence.dao.LoginUserDao;
import com.padcmyanmar.padc7.mmnews.persistence.dao.NewsDao;
import com.padcmyanmar.padc7.mmnews.persistence.typeconverters.ListStringConverter;

@Database(entities = {
                LoginUserVO.class,
                NewsVO.class,
                CommentVO.class
        },
        version = 8)
@TypeConverters({ListStringConverter.class})
public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase INSTANCE;

    public abstract LoginUserDao loginUserDao();

    public abstract NewsDao newsDao();

    public abstract CommentDao commentDao();

    public static NewsDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, NewsDatabase.class, "NewsDabase.DB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public boolean isNewsEmpty() {
        NewsVO news = newsDao().getLatestNews();
        return news == null;
    }
}
