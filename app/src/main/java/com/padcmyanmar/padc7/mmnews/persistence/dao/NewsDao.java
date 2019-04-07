package com.padcmyanmar.padc7.mmnews.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insertNews(List<NewsVO> newsList);

    @Query("SELECT * FROM news WHERE news_id = :newsId")
    public abstract NewsVO getNewsById(String newsId);

    @Query("SELECT * FROM news")
    public abstract List<NewsVO> loadNews();

    @Query("SELECT * FROM news ORDER BY news_id_pk DESC LIMIT 1")
    public abstract NewsVO getLatestNews();

    /**
     * Save news after preparing the foreign key constraints between NewsVO & CommentVO.
     *
     * @param newsList
     * @return size of the news that are being saved.
     */
    public int saveNews(List<NewsVO> newsList, CommentDao commentDao) {
        //Data preparation for foreign key constraints.
        List<CommentVO> allComments = new ArrayList<>();
        for (NewsVO news : newsList) {
            for (CommentVO comment : news.getComments()) {
                comment.setNewsId(news.getNewsId());
            }
            //To save all the comment at once with single DB execution statement.
            allComments.addAll(news.getComments());
        }

        long[] ids = insertNews(newsList);
        long[] commentIds = commentDao.insertComments(allComments);

        return ids.length;
    }

    /**
     * Load a news along with its related comments, favorites & sent-tos - by using newsId.
     * @param newsId
     * @param commentDao
     * @return loaded news.
     */
    public NewsVO loadNewsById(String newsId, CommentDao commentDao) {
        NewsVO news = getNewsById(newsId);
        List<CommentVO> commentList = commentDao.getCommentsByNewsId(newsId);
        news.setComments(commentList);

        return news;
    }
}
