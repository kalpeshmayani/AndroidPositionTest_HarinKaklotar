package com.harinkaklotar.androidposition

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.facebook.stetho.Stetho

class App: Application() {

    companion object{
        private lateinit var instance: App

        fun getInstance(): App{
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }

    fun hasNetworkConnection(): Boolean {
        val activeNetwork =
            (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}