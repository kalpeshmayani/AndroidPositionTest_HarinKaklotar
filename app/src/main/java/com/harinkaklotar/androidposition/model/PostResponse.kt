package com.harinkaklotar.androidposition.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PostResponse(
    @field:SerializedName("PostResponse")
    val postResponse: List<Post> = emptyList()
)

@Parcelize
data class Post(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null
) : Parcelable
