package com.harinkaklotar.androidposition.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harinkaklotar.androidposition.local.dao.CommentDao
import com.harinkaklotar.androidposition.local.dao.PostDao
import com.harinkaklotar.androidposition.local.dao.UserDao
import com.harinkaklotar.androidposition.local.entity.Comment
import com.harinkaklotar.androidposition.local.entity.Post
import com.harinkaklotar.androidposition.local.entity.User

@Database(entities = [Post::class, User::class, Comment::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postDao() : PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
}