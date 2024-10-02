package com.ktm.kgithubmanager.domain.usecase

import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.UserRepository
import com.ktm.kgithubmanager.domain.repository.result.Error
import com.ktm.kgithubmanager.domain.repository.result.Result

class UserUseCase(
    private val userRepository: UserRepository
) {

    /**
     * Fetch users from data source
     * @param startUserId The return users will have id greater than it
     * @param count Number of users per page
     */
    suspend fun fetchUsers(startUserId: Long, count: Int): Result<Pair<List<User>, Long>, Error> {
        return userRepository.fetchUsers(startUserId, count)
    }

    /**
     * Fetch user details from data source
     * @param userName The key for each user
     */
    suspend fun fetchUserDetails(userName: String): Result<User, Error> {
        return userRepository.fetchUserDetails(userName)
    }
}