package com.harinkaklotar.androidposition.api

import com.harinkaklotar.androidposition.model.Comment
import com.harinkaklotar.androidposition.model.Post
import com.harinkaklotar.androidposition.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("comments")
    suspend fun getComments(): Response<List<Comment>>
}