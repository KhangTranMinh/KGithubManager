package com.ktm.kgithubmanager.data.network

import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsResponse
import com.ktm.kgithubmanager.data.network.data.FetchUsersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    /**
     * Return list of users
     * @param perPage number of users
     * @param since the API return users that have id greater than it
     */
    @GET("users")
    suspend fun fetchUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Long,
    ): FetchUsersResponse?

    /**
     * Return user details
     * @param userName The key for each user
     */
    @GET("users/{login_username}")
    suspend fun fetchUserDetails(
        @Path("login_username") userName: String,
    ): FetchUsersDetailsResponse?
}