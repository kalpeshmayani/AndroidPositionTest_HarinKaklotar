package com.harinkaklotar.androidposition.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harinkaklotar.androidposition.App
import com.harinkaklotar.androidposition.api.ApiHelperImpl
import com.harinkaklotar.androidposition.local.DatabaseHelper
import com.harinkaklotar.androidposition.model.Comment
import com.harinkaklotar.androidposition.model.User
import com.harinkaklotar.androidposition.utils.AppMethods
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val apiHelperImpl: ApiHelperImpl,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private var user = MutableLiveData<User>()
    private var comments = MutableLiveData<List<Comment>>()
    private var isLoading = MutableLiveData<Boolean>()
    private var error = MutableLiveData<String>()


    fun getUserAndComments(userId: Int, postId: Int) {
        viewModelScope.launch {

            if (App.getInstance().hasNetworkConnection()) {
                // get data from service
                isLoading.postValue(true)

                val apiUsersResponse = apiHelperImpl.getUsers()
                val apiCommentsResponse = apiHelperImpl.getComments()
                isLoading.postValue(false)

                if (apiUsersResponse.isSuccessful) {
                    // store in database

                    val userFromApi = apiUsersResponse.body()

                    if (userFromApi != null) {
                        insertUser(userFromApi)
                        for (userItem in userFromApi) {
                            if (userItem.id!! == userId) {
                                user.postValue(userItem)
                                break
                            }
                        }
                    }
                } else {
                    error.postValue(apiUsersResponse.message())
                }

                if (apiCommentsResponse.isSuccessful) {
                    // store in database
                    val commentsFromApi = apiCommentsResponse.body()

                    if (commentsFromApi != null) {
                        insertComment(commentsFromApi)
                        val tmpComments = mutableListOf<Comment>()
                        for (commentItem in commentsFromApi) {
                            if (commentItem.postId!! == postId) {
                                tmpComments.add(commentItem)
                            }
                        }
                        comments.postValue(tmpComments)
                    }
                } else {
                    error.postValue(apiCommentsResponse.message())
                }

            } else {
                // get data from local storage
                error.postValue("Internet connection error")
                isLoading.postValue(true)
                val userFromDb = dbHelper.getUser(userId)
                val commentsFromDb = dbHelper.getComments(postId)
                isLoading.postValue(false)
                user.postValue(AppMethods.toUser(userFromDb))
                comments.postValue(AppMethods.toComment(commentsFromDb))
            }
        }
    }

    private fun insertUser(userFromApi: List<User>) {
        viewModelScope.launch {
            val usersList =
                mutableListOf<com.harinkaklotar.androidposition.local.entity.User>()
            for (apiUser in userFromApi) {
                val user = com.harinkaklotar.androidposition.local.entity.User(
                    apiUser.id!!,
                    apiUser.username,
                    apiUser.email,
                    apiUser.phone,
                    apiUser.id
                )
                usersList.add(user)
            }
            dbHelper.insetAllUser(usersList)
        }
    }

    private fun insertComment(commentFromApi: List<Comment>) {
        viewModelScope.launch {
            val commentsList =
                mutableListOf<com.harinkaklotar.androidposition.local.entity.Comment>()
            for (apiUser in commentFromApi) {
                val comment = com.harinkaklotar.androidposition.local.entity.Comment(
                    apiUser.id!!,
                    apiUser.name,
                    apiUser.email,
                    apiUser.body,
                    apiUser.postId
                )
                commentsList.add(comment)
            }
            dbHelper.insetAllComment(commentsList)
        }
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getUser(): LiveData<User> {
        return user
    }

    fun getComments(): LiveData<List<Comment>> {
        return comments
    }

    fun getError(): LiveData<String> {
        return error
    }
}