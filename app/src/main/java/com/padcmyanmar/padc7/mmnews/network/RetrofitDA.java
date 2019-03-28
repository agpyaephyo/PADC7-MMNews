package com.padcmyanmar.padc7.mmnews.network;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;
import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse;
import com.padcmyanmar.padc7.mmnews.network.responses.LoginResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
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
    public void loadNews(int page, String accessToken, GetNewsDelegate newsResponseDelegate) {
        Call<GetNewsResponse> callNewsResponse = mNewsAPI.loadNews(accessToken, page);
        callNewsResponse.enqueue(new NewsCallback<GetNewsResponse, GetNewsDelegate>(newsResponseDelegate) {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                super.onResponse(call, response);
                GetNewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.isResponseSuccess()) {
                    networkDelegate.onSuccess(newsResponse.getNewsList());
                }
            }
        });
        /*
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
        */
    }

    @Override
    public void login(String phoneNumber, String password, LoginDelegate loginDelegate) {
        Call<LoginResponse> callLoginResponse = mNewsAPI.login(phoneNumber, password);
        callLoginResponse.enqueue(new NewsCallback<LoginResponse, LoginDelegate>(loginDelegate) {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                super.onResponse(call, response);
                LoginResponse loginResponse = response.body();
                if (loginResponse != null && loginResponse.isResponseSuccess()) {
                    LoginUserVO loginUser = loginResponse.getLoginUser();
                    networkDelegate.onSuccess(loginUser);
                }
            }
        });
    }

    @Override
    public void register(String phoneNUmber, String name, String password) {

    }
}
