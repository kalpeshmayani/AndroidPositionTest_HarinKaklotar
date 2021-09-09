package com.harinkaklotar.androidposition.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harinkaklotar.androidposition.local.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment where postId = :id")
    suspend fun getComments(id: Int): List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Comment>)
}