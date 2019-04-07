package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate;

import java.util.List;

public class NewsModelImpl extends BaseModel implements NewsModel {

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static final String KEY_NEWS_PAGE = "KEY_NEWS_PAGE";

    private static NewsModelImpl instance;

    private NewsModelImpl(Context context) {
        super(context);
    }

    public static void initNewsModel(Context context) {
        instance = new NewsModelImpl(context);
    }

    public static NewsModelImpl getInstance() {
        if (instance == null) {
            throw new RuntimeException("NewsModelImpl should be initialized before using it.");
        }
        return instance;
    }

    @Override
    public void addCommentNews(NewsVO news, CommentVO comment) {

    }

    @Override
    public void favoriteNews(NewsVO news, FavoriteVO favorite) {

    }

    @Override
    public void sendNewsTo(NewsVO news, SendToVO sendTo) {

    }

    @Override
    public @Nullable
    List<NewsVO> getNews(final NewsDelegate newsDelegate, final boolean isForce) {
        int newsPage = getNewsPage();
        if (isForce) {
            newsPage = 1;
        }

        if (mNewsDB.isNewsEmpty() || isForce) {
            mDataAgent.loadNews(newsPage,
                    DUMMY_ACCESS_TOKEN,
                    new GetNewsDelegate() {
                        @Override
                        public void onSuccess(List<NewsVO> newsList) {
                            if (!isForce) {
                                setNewsPage(getNewsPage() + 1);
                            }

                            int insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao());
                            List<NewsVO> newsListDB = mNewsDB.newsDao().loadNews();
                            newsDelegate.onSuccess(newsListDB);
                        }

                        @Override
                        public void onFail(String msg) {
                            newsDelegate.onError(msg);
                        }
                    });
        } else {
            List<NewsVO> newsList = mNewsDB.newsDao().loadNews();
            return newsList;
        }

        return null;
    }

    @Override
    public void loadMoreNews(final NewsDelegate newsDelegate) {
        mDataAgent.loadNews(getNewsPage(),
                DUMMY_ACCESS_TOKEN,
                new GetNewsDelegate() {
                    @Override
                    public void onSuccess(List<NewsVO> newsList) {
                        setNewsPage(getNewsPage() + 1);
                        int insertedNewsCount = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao());
                        List<NewsVO> newsListDB = mNewsDB.newsDao().loadNews();
                        newsDelegate.onSuccess(newsListDB);
                    }

                    @Override
                    public void onFail(String msg) {
                        newsDelegate.onError(msg);
                    }
                });
    }

    private int getNewsPage() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return sharedPreferences.getInt(KEY_NEWS_PAGE, 1);
    }

    private void setNewsPage(int newPageNumber) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences.edit().putInt(KEY_NEWS_PAGE, newPageNumber).apply();
    }
}
