package com.harinkaklotar.androidposition.api

class ApiHelperImpl : ApiHelper {
    override suspend fun getPosts() = RetrofitBuilder.api.getPosts()
    override suspend fun getUsers() = RetrofitBuilder.api.getUsers()
    override suspend fun getComments() = RetrofitBuilder.api.getComments()
}