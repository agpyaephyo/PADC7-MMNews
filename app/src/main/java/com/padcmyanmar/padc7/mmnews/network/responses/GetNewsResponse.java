package com.padcmyanmar.padc7.mmnews.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import java.util.List;

public class GetNewsResponse extends BaseResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("mmNews")
    private List<NewsVO> newsList;

    public int getPage() {
        return page;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
