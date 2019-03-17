package com.padcmyanmar.padc7.mmnews.views.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseNewsViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_news_hero_image)
    ImageView ivNewsHeroImage;

    public BaseNewsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(NewsVO data) {
        tvBriefNews.setText(data.getBrief());

        if(ivNewsHeroImage != null) {
            Glide.with(itemView)
                    .load(data.getHeroImage())
                    .into(ivNewsHeroImage);
        }
    }
}
