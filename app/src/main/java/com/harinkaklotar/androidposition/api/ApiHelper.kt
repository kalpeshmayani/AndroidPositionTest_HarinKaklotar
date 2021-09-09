package com.harinkaklotar.androidposition.api

import com.harinkaklotar.androidposition.model.Comment
import com.harinkaklotar.androidposition.model.Post
import com.harinkaklotar.androidposition.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getPosts(): Response<List<Post>>
    suspend fun getUsers(): Response<List<User>>
    suspend fun getComments(): Response<List<Comment>>
}