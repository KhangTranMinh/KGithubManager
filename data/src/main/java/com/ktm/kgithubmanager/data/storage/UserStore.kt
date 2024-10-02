package com.ktm.kgithubmanager.data.storage

import com.ktm.kgithubmanager.domain.entity.User

interface UserStore {

    /**
     * Return list of user
     * @param startUserId The return users will have id greater than it
     * @param count the maximum users
     */
    suspend fun getUsers(startUserId: Long, count: Int): List<User>

    /**
     * Save list or user
     */
    suspend fun saveUsers(users: List<User>)
}