package com.padcmyanmar.padc7.mmnews.network;

import com.padcmyanmar.padc7.mmnews.delegates.NewsResponseDelegate;
import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDA implements NewsDataAgent {

    private static RetrofitDA objInstance;

    private NewsAPI mNewsAPI;

    private RetrofitDA() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mNewsAPI = retrofit.create(NewsAPI.class);
    }

    public static RetrofitDA getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDA();
        }
        return objInstance;
    }

    @Override
    public void loadNews(int page, String accessToken, final NewsResponseDelegate newsResponseDelegate) {
        Call<GetNewsResponse> callNewsResponse = mNewsAPI.loadNews(accessToken, page);
        callNewsResponse.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null) {
                    if (newsResponse.isResponseSuccess()) {
                        newsResponseDelegate.onSuccess(newsResponse.getNewsList());
                    } else {
                        newsResponseDelegate.onFail(newsResponse.getMessage());
                    }
                } else {
                    newsResponseDelegate.onFail("Response is null.");
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                newsResponseDelegate.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void login(String phoneNumber, String password) {

    }

    @Override
    public void register(String phoneNUmber, String name, String password) {

    }
}
