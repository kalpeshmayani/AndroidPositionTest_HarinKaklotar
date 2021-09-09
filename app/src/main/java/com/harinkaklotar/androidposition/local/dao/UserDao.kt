package com.harinkaklotar.androidposition.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harinkaklotar.androidposition.local.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user where userId = :id")
    suspend fun getUser(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<User>)
}