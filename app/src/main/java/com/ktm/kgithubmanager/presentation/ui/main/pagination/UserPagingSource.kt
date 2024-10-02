package com.ktm.kgithubmanager.presentation.ui.main.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.result.Result
import com.ktm.kgithubmanager.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserPagingSource(
    private val userUseCase: UserUseCase
) : PagingSource<Long, User>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, User> {
        val startUserId = params.key ?: 0

        return try {
            val result = withContext(Dispatchers.Default) {
                // make sure that this action runs in background thread
                userUseCase.fetchUsers(startUserId, params.loadSize)
            }
            if (result is Result.Success) {
                val users = result.data.first

                // next fetch action (from local storage or API) will start from the latest ID
                val lastUserId = result.data.second

                LoadResult.Page(
                    data = users,
                    prevKey = null,
                    nextKey = if (users.isEmpty()) null else lastUserId
                )
            } else {
                LoadResult.Error(Exception("Fetch data fail"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, User>): Long? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}