package com.harinkaklotar.androidposition.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harinkaklotar.androidposition.databinding.ItemPostBinding
import com.harinkaklotar.androidposition.model.Post

class PostAdapter(private val click: (Post) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var mPostItems: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding: ItemPostBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val mPost = mPostItems[position]
        holder.title.text = mPost.title
        holder.body.text = mPost.body
        holder.itemView.setOnClickListener {
            click(mPost)
        }
    }

    override fun getItemCount(): Int {
        return mPostItems.size
    }

    fun setItems(items: List<Post>) {
        mPostItems = items
        notifyDataSetChanged()
    }

    class PostViewHolder(binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        var title: TextView = binding.tvTitle
        var body: TextView = binding.tvBody
    }
}