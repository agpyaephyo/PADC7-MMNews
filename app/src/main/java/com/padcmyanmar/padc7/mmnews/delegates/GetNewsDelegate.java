package com.padcmyanmar.padc7.mmnews.delegates;

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import java.util.List;

public interface GetNewsDelegate extends BaseNetworkDelegate {

    void onSuccess(List<NewsVO> newsList);
}
