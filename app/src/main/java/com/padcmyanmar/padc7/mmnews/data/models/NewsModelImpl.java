package com.padcmyanmar.padc7.mmnews.data.models;

import android.support.annotation.Nullable;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;
import com.padcmyanmar.padc7.mmnews.network.HttpUrlConnectionDA;
import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsModelImpl extends BaseModel implements NewsModel {

    private static NewsModelImpl objInstance;

    private Map<String, NewsVO> mNews;

    private NewsDataAgent mDataAgent;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private NewsModelImpl() {
        mNews = new HashMap<>();
        mDataAgent = HttpUrlConnectionDA.getInstance();
    }

    public static NewsModelImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new NewsModelImpl();
        }
        return objInstance;
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
    List<NewsVO> getNews(final NewsDelegate newsDelegate, boolean isForce) {
        if (mNews.isEmpty() || isForce) {
                mDataAgent.loadNews(1,
                    DUMMY_ACCESS_TOKEN,
                    new HttpUrlConnectionDA.GetNewsTask.NewsResponseDelegate() {
                        @Override
                        public void onSuccess(List<NewsVO> newsList) {
                            for (NewsVO news : newsList) {
                                mNews.put(news.getNewsId(), news);
                            }
                            newsDelegate.onNewsFetchedFromNetwork(new ArrayList<>(mNews.values()));
                        }

                        @Override
                        public void onFail(String msg) {
                            newsDelegate.onErrorNewsFetch(msg);
                        }
                    });
        } else {
            return new ArrayList<>(mNews.values());
        }

        return null;
    }
}
