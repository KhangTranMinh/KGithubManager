package com.ktm.kgithubmanager.data.repository

import com.ktm.kgithubmanager.data.network.api.FetchUserDetailsApi
import com.ktm.kgithubmanager.data.network.api.FetchUsersApi
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsRequest
import com.ktm.kgithubmanager.data.network.data.FetchUsersRequest
import com.ktm.kgithubmanager.data.storage.UserStore
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.UserRepository
import com.ktm.kgithubmanager.domain.repository.result.Error
import com.ktm.kgithubmanager.domain.repository.result.Result
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val fetchUsersApi: FetchUsersApi,
    private val fetchUserDetailsApi: FetchUserDetailsApi,
    private val userStore: UserStore
) : UserRepository {

    override suspend fun fetchUsers(
        startUserId: Long,
        count: Int
    ): Result<Pair<List<User>, Long>, Error> {
        // fetch users from local storage first
        val users = userStore.getUsers(startUserId, count)
        return if (users.isEmpty()) {
            // if there is no data, fetch from API
            fetchRemote(startUserId, count)
        } else {
            Result.Success(Pair(users, users.last().id))
        }
    }

    override suspend fun fetchUserDetails(userName: String): Result<User, Error> {
        val response = fetchUserDetailsApi.execute(
            FetchUsersDetailsRequest(
                loginUserName = userName
            )
        )
        return if (response == null) {
            Result.Error(Error.GeneralError(NullPointerException()))
        } else if (response.isSuccess()) {
            Result.Success(response.toUser())
        } else {
            Result.Error(Error.ApiError(response.errorCode))
        }
    }

    private suspend fun fetchRemote(
        startUserId: Long,
        count: Int
    ): Result<Pair<List<User>, Long>, Error> {
        val users = ArrayList<User>()
        val response = fetchUsersApi.execute(
            FetchUsersRequest(
                since = startUserId,
                perPage = count,
            )
        )
        return if (response == null) {
            Result.Error(Error.GeneralError(NullPointerException()))
        } else if (response.isSuccess()) {
            response.forEach {
                users.add(it.toUser())
            }

            // after getting response from API, save to local storage
            userStore.saveUsers(users)

            Result.Success(Pair(users, users.last().id))
        } else {
            Result.Error(Error.ApiError(response.errorCode))
        }
    }
}