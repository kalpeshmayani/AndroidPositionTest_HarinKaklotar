package com.harinkaklotar.androidposition.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harinkaklotar.androidposition.R
import com.harinkaklotar.androidposition.api.ApiHelperImpl
import com.harinkaklotar.androidposition.databinding.ActivityDetailBinding
import com.harinkaklotar.androidposition.local.DatabaseBuilder
import com.harinkaklotar.androidposition.local.DatabaseHelperImpl
import com.harinkaklotar.androidposition.model.Post
import com.harinkaklotar.androidposition.ui.adapter.CommentAdapter
import com.harinkaklotar.androidposition.utils.ViewModelFactory
import com.harinkaklotar.androidposition.utils.extensions.gone
import com.harinkaklotar.androidposition.utils.extensions.visible

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        post = intent.extras?.getParcelable<Post>("post")!!

        initViews()
        initViewModel()
        observeViewModel()
    }

    private fun initViews() {
        binding.tvTitle.text = post.title
        binding.tvBody.text = post.body

        commentAdapter = CommentAdapter()
        binding.recyclerViewComments.adapter = commentAdapter
    }

    private fun initViewModel() {
        val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        val factory = ViewModelFactory(ApiHelperImpl(), dbHelper)
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.getUserAndComments(post.userId!!, post.id!!)

        viewModel.getUser().observe(this, Observer {
            binding.tvUserName.text = getString(R.string.by_author, it.username)
        })

        viewModel.getComments().observe(this, Observer {
            commentAdapter.setItems(it)
        })

        viewModel.isLoading().observe(this, Observer {
            if (it)
                binding.progressBar.visible()
            else
                binding.progressBar.gone()
        })

        viewModel.getError().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}