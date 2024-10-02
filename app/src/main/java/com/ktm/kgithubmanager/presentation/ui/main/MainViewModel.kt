package com.ktm.kgithubmanager.presentation.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.UserRepository
import com.ktm.kgithubmanager.domain.usecase.UserUseCase
import com.ktm.kgithubmanager.presentation.ui.base.BaseViewModel
import com.ktm.kgithubmanager.presentation.ui.main.pagination.UserPagingSource
import com.ktm.kgithubmanager.presentation.util.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {

    private val userUseCase = UserUseCase(userRepository)

    var pagingDataFlow: Flow<PagingData<User>>? = null

    fun fetchUsers() {
        pagingDataFlow = Pager(
            PagingConfig(
                pageSize = Const.ITEM_PER_PAGE,
                initialLoadSize = Const.ITEM_PER_PAGE
            )
        ) {
            UserPagingSource(userUseCase)
        }.flow.cachedIn(viewModelScope)
    }
}