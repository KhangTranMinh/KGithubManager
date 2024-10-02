package com.ktm.kgithubmanager.data.network.api

import com.ktm.kgithubmanager.data.network.ApiConfig
import com.ktm.kgithubmanager.data.network.ErrorCode
import com.ktm.kgithubmanager.data.network.UserService
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsRequest
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsResponse
import com.ktm.kgithubmanager.data.network.mock.MockResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class FetchUserDetailsApi @Inject constructor(
    private val userService: UserService
) : BaseApi<FetchUsersDetailsRequest, FetchUsersDetailsResponse>() {

    override suspend fun execute(request: FetchUsersDetailsRequest): FetchUsersDetailsResponse? {
        return runCatching {
            if (ApiConfig.FOR_DEBUG) delay(500L)
            userService.fetchUserDetails(request.loginUserName)
        }.getOrElse {
            if (ApiConfig.FOR_DEBUG) {
                MockResponse.fetchUserDetails(request.loginUserName)
            } else {
                FetchUsersDetailsResponse().apply {
                    errorCode = ErrorCode.UNKNOWN
                }
            }
        }
    }
}