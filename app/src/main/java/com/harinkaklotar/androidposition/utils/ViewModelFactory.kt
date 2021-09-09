package com.harinkaklotar.androidposition.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harinkaklotar.androidposition.api.ApiHelperImpl
import com.harinkaklotar.androidposition.local.DatabaseHelper
import com.harinkaklotar.androidposition.ui.DetailsViewModel
import com.harinkaklotar.androidposition.ui.MainViewModel

class ViewModelFactory(private val apiHelperImpl: ApiHelperImpl, private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelperImpl, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(apiHelperImpl, dbHelper) as T
        }

        throw IllegalArgumentException("Invalid view model")

    }
}