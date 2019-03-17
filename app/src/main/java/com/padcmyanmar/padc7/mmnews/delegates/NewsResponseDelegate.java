package com.padcmyanmar.padc7.mmnews.delegates;

import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import java.util.List;

public interface NewsResponseDelegate {

    void onSuccess(List<NewsVO> newsList);

    void onFail(String msg);
}
