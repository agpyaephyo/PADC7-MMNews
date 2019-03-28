package com.padcmyanmar.padc7.mmnews.network;

import com.padcmyanmar.padc7.mmnews.delegates.BaseNetworkDelegate;
import com.padcmyanmar.padc7.mmnews.network.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NewsCallback<T extends BaseResponse, W extends BaseNetworkDelegate> implements Callback<T> {

    W networkDelegate;

    NewsCallback(W networkDelegate) {
        this.networkDelegate = networkDelegate;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T newsResponse = response.body();
        if (newsResponse == null) {
            networkDelegate.onFail("Response is null.");
        } else {
            if (!newsResponse.isResponseSuccess()) {
                networkDelegate.onFail(newsResponse.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        networkDelegate.onFail(t.getMessage());
    }
}
