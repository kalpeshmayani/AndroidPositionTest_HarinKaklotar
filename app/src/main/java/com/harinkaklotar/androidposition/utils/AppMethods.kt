package com.harinkaklotar.androidposition.utils

import com.harinkaklotar.androidposition.local.entity.Comment
import com.harinkaklotar.androidposition.local.entity.Post
import com.harinkaklotar.androidposition.local.entity.User

object AppMethods {

    fun toPost(postItems: List<Post>): List<com.harinkaklotar.androidposition.model.Post> {
        if (postItems.isNotEmpty()) {
            val tmpPost = mutableListOf<com.harinkaklotar.androidposition.model.Post>()
            for (post in postItems) {
                tmpPost.add(
                    com.harinkaklotar.androidposition.model.Post(
                        post.id,
                        post.title,
                        post.body,
                        post.userId
                    )
                )
            }
            return tmpPost
        }
        return emptyList()
    }

    fun toUser(user: User): com.harinkaklotar.androidposition.model.User {
        if (user != null) {
            return com.harinkaklotar.androidposition.model.User(
                username = user.username,
                email = user.email,
                phone = user.phone,
                id = user.userId
            )
        }
        return com.harinkaklotar.androidposition.model.User()
    }

    fun toComment(commentItems: List<Comment>): List<com.harinkaklotar.androidposition.model.Comment> {
        if (commentItems.isNotEmpty()) {
            val tmpComment = mutableListOf<com.harinkaklotar.androidposition.model.Comment>()
            for (comment in commentItems) {
                tmpComment.add(
                    com.harinkaklotar.androidposition.model.Comment(
                        name = comment.name,
                        postId = comment.postId,
                        id = comment.id,
                        body = comment.body,
                        email = comment.email
                    )
                )
            }
            return tmpComment
        }
        return emptyList()
    }

}