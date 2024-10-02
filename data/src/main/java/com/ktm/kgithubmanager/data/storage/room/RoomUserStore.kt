package com.ktm.kgithubmanager.data.storage.room

import com.ktm.kgithubmanager.data.storage.UserStore
import com.ktm.kgithubmanager.data.storage.room.db.UserDao
import com.ktm.kgithubmanager.data.storage.room.model.UserDbModel
import com.ktm.kgithubmanager.domain.entity.User
import javax.inject.Inject

class RoomUserStore @Inject constructor(
    private val userDao: UserDao
) : UserStore {

    override suspend fun getUsers(startUserId: Long, count: Int): List<User> {
        val users = arrayListOf<User>()
        userDao.getUsers(startUserId, count).forEach {
            users.add(it.toUser())
        }
        return users
    }

    override suspend fun saveUsers(users: List<User>) {
        val userDbModels = ArrayList<UserDbModel>()
        users.forEach {
            userDbModels.add(UserDbModel.fromUser(it))
        }
        userDao.insertAll(*userDbModels.toTypedArray())
    }
}