package com.ktm.kgithubmanager.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ktm.kgithubmanager.databinding.ActivityDetailsBinding
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.presentation.ui.base.BaseActivity
import com.ktm.kgithubmanager.presentation.util.ActivityExtrasKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()

    private var userName: String = ""

    override val loadingView: View
        get() = binding.loading

    override val mainView: View
        get() = binding.main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        userName = intent.extras?.getString(ActivityExtrasKey.USER_NAME) ?: ""

        updateUiByState()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        showLoading()
        viewModel.fetchUserDetails(userName)
    }

    private fun updateUiByState() {
        lifecycleScope.launch {
            viewModel.detailsUiState.collect { uiState ->
                when (uiState) {
                    DetailsUiState.Default -> {
                    }

                    is DetailsUiState.Success -> {
                        hideLoading()
                        updateUi(uiState.user)
                    }

                    is DetailsUiState.Error -> {
                    }
                }
            }
        }
    }

    private fun updateUi(user: User) {
        ensureActivityActive {
            binding.apply {
                main.visibility = View.VISIBLE
                tvName.text = user.userName
                tvLocation.text = user.location
                tvFollowers.text = user.followers.let {
                    if (it > 1000) "1000+" else it.toString()
                }
                tvFollowing.text = user.following.let {
                    if (it > 1000) "1000+" else it.toString()
                }
                tvBlog.text = user.blogUrl
                Glide.with(ivAvatar.context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(ivAvatar)
            }
        }
    }
}