package com.padcmyanmar.padc7.mmnews.views.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate;

public class NewsViewHolder extends BaseNewsViewHolder {

    private NewsItemDelegate mDelegate;

    public NewsViewHolder(@NonNull View itemView, @NonNull NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate = newsItemDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapNewsItem();
            }
        });
    }
}
