package com.harinkaklotar.androidposition.local

import com.harinkaklotar.androidposition.local.entity.Comment
import com.harinkaklotar.androidposition.local.entity.Post
import com.harinkaklotar.androidposition.local.entity.User

interface DatabaseHelper {
    suspend fun getPosts(): List<Post>
    suspend fun insertAllPost(postList: List<Post>)

    suspend fun getUser(id: Int): User
    suspend fun insetAllUser(userList: List<User>)

    suspend fun getComments(id: Int): List<Comment>
    suspend fun insetAllComment(commentList: List<Comment>)
}