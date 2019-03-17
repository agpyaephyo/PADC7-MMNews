package com.padcmyanmar.padc7.mmnews.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.delegates.NewsResponseDelegate;
import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDA implements NewsDataAgent {

    private static OkHttpDA objInstance;

    private OkHttpClient mHttpClient;

    private OkHttpDA() {
        mHttpClient = new OkHttpClient.Builder() //1.
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDA getInstance() {
        if (objInstance == null) {
            objInstance = new OkHttpDA();
        }
        return objInstance;
    }

    @Override
    public void loadNews(int page, String accessToken, NewsResponseDelegate newsResponseDelegate) {
        new OkHttpDA.GetNewsTask(accessToken, page,
                newsResponseDelegate, mHttpClient).execute();
    }

    @Override
    public void login(String phoneNumber, String password) {

    }

    @Override
    public void register(String phoneNUmber, String name, String password) {

    }

    public static class GetNewsTask extends AsyncTask<Void, Void, GetNewsResponse> {

        private String accessToken;
        private int page;
        private NewsResponseDelegate newsResponseDelegate;
        private OkHttpClient okHttpClient;

        public GetNewsTask(String accessToken, int page,
                           NewsResponseDelegate newsResponseDelegate,
                           OkHttpClient okHttpClient) {
            this.accessToken = accessToken;
            this.page = page;
            this.newsResponseDelegate = newsResponseDelegate;
            this.okHttpClient = okHttpClient;
        }

        @Override
        protected GetNewsResponse doInBackground(Void... voids) {
            RequestBody formBody = new FormBody.Builder() //2.
                    .add(PARAM_ACCESS_TOKEN, accessToken)
                    .add(PARAM_PAGE, String.valueOf(page))
                    .build();

            Request request = new Request.Builder() //3
                    .url(MMNEWS_BASE_URL + GET_NEWS)
                    .post(formBody)
                    .build();

            try {
                Response response = okHttpClient.newCall(request).execute(); //4.
                if (response.isSuccessful()) {
                    String responseString = response.body().string();


                    GetNewsResponse newsResponse = new Gson().fromJson(responseString, GetNewsResponse.class);
                    return newsResponse;
                } /* else {
                    AttractionModel.getInstance().notifyErrorInLoadingAttractions(response.message());
                }*/
            } catch (IOException e) {
                /*
                Log.e(MyanmarAttractionsApp.TAG, e.getMessage());
                AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
                */
            }

            return null;
        }

        @Override
        protected void onPostExecute(GetNewsResponse newsResponse) {
            super.onPostExecute(newsResponse);
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
    }
}
