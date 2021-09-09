package com.harinkaklotar.androidposition.local

import com.harinkaklotar.androidposition.local.entity.Comment
import com.harinkaklotar.androidposition.local.entity.Post
import com.harinkaklotar.androidposition.local.entity.User

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    // Post
    override suspend fun getPosts(): List<Post> = appDatabase.postDao().getAll()
    override suspend fun insertAllPost(postItems: List<Post>) = appDatabase.postDao().insertAll(postItems)
    // User
    override suspend fun getUser(id: Int) = appDatabase.userDao().getUser(id)
    override suspend fun insetAllUser(userList: List<User>) = appDatabase.userDao().insertAll(userList)
    // Comment
    override suspend fun getComments(id: Int) = appDatabase.commentDao().getComments(id)
    override suspend fun insetAllComment(commentList: List<Comment>) = appDatabase.commentDao().insertAll(commentList)

}