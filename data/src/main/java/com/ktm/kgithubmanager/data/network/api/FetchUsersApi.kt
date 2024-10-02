package com.ktm.kgithubmanager.data.network.api

import com.ktm.kgithubmanager.data.network.ApiConfig
import com.ktm.kgithubmanager.data.network.ErrorCode
import com.ktm.kgithubmanager.data.network.UserService
import com.ktm.kgithubmanager.data.network.data.FetchUsersRequest
import com.ktm.kgithubmanager.data.network.data.FetchUsersResponse
import com.ktm.kgithubmanager.data.network.mock.MockResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class FetchUsersApi @Inject constructor(
    private val userService: UserService
) : BaseApi<FetchUsersRequest, FetchUsersResponse>() {

    override suspend fun execute(request: FetchUsersRequest): FetchUsersResponse? {
        return runCatching {
            if (ApiConfig.FOR_DEBUG) delay(2000L)
            userService.fetchUsers(
                perPage = request.perPage,
                since = request.since
            )
        }.getOrElse {
            if (ApiConfig.FOR_DEBUG) {
                MockResponse.fetchUsers()
            } else {
                FetchUsersResponse().apply {
                    errorCode = ErrorCode.UNKNOWN
                }
            }
        }
    }
}