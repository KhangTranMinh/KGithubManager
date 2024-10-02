package com.ktm.kgithubmanager.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktm.kgithubmanager.databinding.ActivityMainBinding
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.presentation.ui.base.BaseActivity
import com.ktm.kgithubmanager.presentation.ui.details.DetailsActivity
import com.ktm.kgithubmanager.presentation.ui.main.adapter.LoadingAdapter
import com.ktm.kgithubmanager.presentation.ui.main.adapter.UserAdapter
import com.ktm.kgithubmanager.presentation.util.ActivityExtrasKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val userAdapter = UserAdapter()

    override val loadingView: View
        get() = binding.loading

    override val mainView: View
        get() = binding.recyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initRecyclerView()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        showLoading()
        viewModel.fetchUsers()

        lifecycleScope.launch {
            launch {
                // check the LoadState to dismiss the loading view
                userAdapter.loadStateFlow.collect {
                    if (it.refresh is LoadState.NotLoading) hideLoading()
                }
            }

            launch {
                // collect paging data and update RecyclerView
                viewModel.pagingDataFlow?.collectLatest {
                    userAdapter.submitData(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        val context = this
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            userAdapter.onItemClickListener =
                object : UserAdapter.OnItemClickListener {
                    override fun onItemClicked(user: User) {
                        val intent = Intent(context, DetailsActivity::class.java)
                        intent.putExtra(ActivityExtrasKey.USER_NAME, user.userName)
                        startActivity(intent)
                    }
                }
            adapter = userAdapter.withLoadStateFooter(LoadingAdapter())
        }
    }
}