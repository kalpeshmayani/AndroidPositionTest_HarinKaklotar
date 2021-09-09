package com.harinkaklotar.androidposition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harinkaklotar.androidposition.databinding.ItemCommentBinding
import com.harinkaklotar.androidposition.model.Comment

class CommentAdapter :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var mCommentItems: List<Comment> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding: ItemCommentBinding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val mPost = mCommentItems[position]
        holder.name.text = mPost.name
        holder.comment.text = mPost.body
    }

    override fun getItemCount(): Int {
        return mCommentItems.size
    }

    fun setItems(items: List<Comment>) {
        mCommentItems = items
        notifyDataSetChanged()
    }

    class CommentViewHolder(binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.tvName
        var comment: TextView = binding.tvComment
    }
}