package com.padcmyanmar.padc7.mmnews.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertComments(List<CommentVO> comments);

    @Delete
    int deleteComments(List<CommentVO> comments);

    @Delete
    int deleteComment(CommentVO comment);

    @Query("DELETE FROM comments WHERE news_id = :newsId")
    int deleteCommentsByNewsId(String newsId);

    @Query("SELECT * FROM comments WHERE news_id = :newsId")
    List<CommentVO> getCommentsByNewsId(String newsId);
}
