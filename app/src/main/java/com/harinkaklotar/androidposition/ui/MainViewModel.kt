package com.harinkaklotar.androidposition.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harinkaklotar.androidposition.App
import com.harinkaklotar.androidposition.api.ApiHelperImpl
import com.harinkaklotar.androidposition.local.DatabaseHelper
import com.harinkaklotar.androidposition.model.Post
import com.harinkaklotar.androidposition.utils.AppMethods
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiHelperImpl: ApiHelperImpl,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private var postItems = MutableLiveData<List<Post>>()
    private var error = MutableLiveData<String>()
    private var isLoading = MutableLiveData<Boolean>()

    init {
        getPostData()
    }

    private fun getPostData() {

        viewModelScope.launch {

            if (App.getInstance().hasNetworkConnection()) {
                isLoading.postValue(true)
                val serviceResponse = apiHelperImpl.getPosts()

                if (serviceResponse.isSuccessful) {
                    isLoading.postValue(false)

                    val postFromApi = serviceResponse.body()
                    if (postFromApi != null) {
                        val postItemsToInsertInDB =
                            mutableListOf<com.harinkaklotar.androidposition.local.entity.Post>()
                        for (apiPost in postFromApi) {
                            val post = com.harinkaklotar.androidposition.local.entity.Post(
                                apiPost.id!!,
                                apiPost.title,
                                apiPost.body,
                                apiPost.userId
                            )
                            postItemsToInsertInDB.add(post)
                        }
                        dbHelper.insertAllPost(postItemsToInsertInDB)
                        postItems.postValue(postFromApi)
                    }

                } else {
                    isLoading.postValue(false)
                    error.postValue(serviceResponse.errorBody()?.toString())
                }

            } else {
                error.postValue("Internet connection error")
                isLoading.postValue(true)
                val postFromDb = dbHelper.getPosts()
                isLoading.postValue(false)
                postItems.postValue(AppMethods.toPost(postFromDb))
            }

        }

    }


    fun getPost(): LiveData<List<Post>> {
        return postItems
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getError(): LiveData<String>{
        return error
    }

}