package com.padcmyanmar.padc7.mmnews.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate;
import com.padcmyanmar.padc7.mmnews.views.holders.BaseNewsViewHolder;
import com.padcmyanmar.padc7.mmnews.views.holders.NewsSmallViewHolder;
import com.padcmyanmar.padc7.mmnews.views.holders.NewsViewHolder;

public class NewsAdapter extends RecyclerView.Adapter<BaseNewsViewHolder> {

    private static final int REGULAR_NEWS_VIEW = 1234;
    private static final int SMALL_NEWS_VIEW = 2345;

    private NewsItemDelegate mNewsItemDelegate;

    public NewsAdapter(NewsItemDelegate newsItemDelegate) {
        mNewsItemDelegate = newsItemDelegate;
    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case REGULAR_NEWS_VIEW:
                View itemView = layoutInflater.inflate(R.layout.view_item_news, viewGroup, false);
                return new NewsViewHolder(itemView, mNewsItemDelegate);
            case SMALL_NEWS_VIEW:
                View itemViewSmall = layoutInflater.inflate(R.layout.view_item_news_small, viewGroup, false);
                return new NewsSmallViewHolder(itemViewSmall);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return REGULAR_NEWS_VIEW;
        } else {
            return SMALL_NEWS_VIEW;
        }
    }
}
