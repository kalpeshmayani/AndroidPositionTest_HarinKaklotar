package com.harinkaklotar.androidposition.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harinkaklotar.androidposition.local.entity.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)
}