package com.padcmyanmar.padc7.mmnews.network;

import com.padcmyanmar.padc7.mmnews.network.responses.GetNewsResponse;
import com.padcmyanmar.padc7.mmnews.network.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsAPI {

    String EP_GET_NEWS = "v1/getMMNews.php";
    String EP_LOGIN = "v1/login.php";

    String PARAM_ACCESS_TOKEN = "access_token";
    String PARAM_PAGE = "page";
    String PARAM_PHONE_NO = "phoneNo";
    String PARAM_PASSWORD = "password";

    @FormUrlEncoded
    @POST(EP_GET_NEWS)
    Call<GetNewsResponse> loadNews(@Field(PARAM_ACCESS_TOKEN) String accessToken,
                                   @Field(PARAM_PAGE) int page);

    @FormUrlEncoded
    @POST(EP_LOGIN)
    Call<LoginResponse> login(@Field(PARAM_PHONE_NO) String phoneNo,
                              @Field(PARAM_PASSWORD) String password);
}
