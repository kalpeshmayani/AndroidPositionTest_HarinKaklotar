package com.harinkaklotar.androidposition.api

import com.harinkaklotar.androidposition.BuildConfig
import com.harinkaklotar.androidposition.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val loggingInterceptor by lazy {
        if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val okHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retorfit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retorfit.create(ApiService::class.java)
    }
}