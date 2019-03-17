package com.padcmyanmar.padc7.mmnews.network;

import com.padcmyanmar.padc7.mmnews.delegates.NewsResponseDelegate;

public interface NewsDataAgent {

    String MMNEWS_BASE_URL = "http://padcmyanmar.com/padc-3/mm-news/apis/";

    String GET_NEWS = "v1/getMMNews.php";

    String PARAM_ACCESS_TOKEN = "access_token";
    String PARAM_PAGE = "page";

    void loadNews(int page, String accessToken,
                  NewsResponseDelegate newsResponseDelegate);

    void login(String phoneNumber, String password);

    void register(String phoneNUmber, String name, String password);
}
