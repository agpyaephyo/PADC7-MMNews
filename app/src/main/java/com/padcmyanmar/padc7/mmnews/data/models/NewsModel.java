package com.padcmyanmar.padc7.mmnews.data.models;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;

import java.util.List;

public interface NewsModel {

    void addCommentNews(NewsVO news, CommentVO comment);

    void favoriteNews(NewsVO news, FavoriteVO favorite);

    void sendNewsTo(NewsVO news, SendToVO sendTo);

    //Get News.
    List<NewsVO> getNews(NewsDelegate newsDelegate, boolean isForce);

    void loadMoreNews(NewsDelegate newsDelegate);

    interface NewsDelegate {

        void onSuccess(List<NewsVO> newsList);

        void onError(String msg);
    }
}
