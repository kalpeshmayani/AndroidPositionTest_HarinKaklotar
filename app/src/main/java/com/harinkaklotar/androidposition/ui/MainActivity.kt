package com.harinkaklotar.androidposition.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harinkaklotar.androidposition.api.ApiHelperImpl
import com.harinkaklotar.androidposition.databinding.ActivityMainBinding
import com.harinkaklotar.androidposition.local.DatabaseBuilder
import com.harinkaklotar.androidposition.local.DatabaseHelperImpl
import com.harinkaklotar.androidposition.model.Post
import com.harinkaklotar.androidposition.ui.adapter.PostAdapter
import com.harinkaklotar.androidposition.utils.ViewModelFactory
import com.harinkaklotar.androidposition.utils.extensions.gone
import com.harinkaklotar.androidposition.utils.extensions.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initViewModel()
        observeViewModel()

    }

    private fun initViews() {
        postAdapter = PostAdapter() {
            gotoDetailsPage(it)
        }
        binding.recyclerView.adapter = postAdapter
    }

    private fun initViewModel() {
        val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        val factory = ViewModelFactory(ApiHelperImpl(), dbHelper)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        viewModel.getPost().observe(this, Observer {
            if (it.isNotEmpty()) {
                postAdapter.setItems(it)
            }
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

    private fun gotoDetailsPage(post: Post) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

}