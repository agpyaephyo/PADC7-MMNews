package com.padcmyanmar.padc7.mmnews.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate;
import com.padcmyanmar.padc7.mmnews.views.holders.NewsViewHolder;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private NewsItemDelegate mNewsItemDelegate;

    public NewsAdapter(NewsItemDelegate newsItemDelegate) {
        mNewsItemDelegate = newsItemDelegate;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_item_news, viewGroup, false);
        return new NewsViewHolder(itemView, mNewsItemDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }
}
