package com.ktm.kgithubmanager.data.storage.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ktm.kgithubmanager.data.storage.room.model.UserDbModel

@Dao
interface UserDao {

    /**
     * Insert all input users
     */
    @Insert
    fun insertAll(vararg userDbModels: UserDbModel)

    /**
     * Return list of user
     * @param startUserId The return users will have id greater than it
     * @param limit the maximum users
     */
    @Query("SELECT * FROM userdbmodel WHERE id > :startUserId  LIMIT :limit")
    fun getUsers(startUserId: Long, limit: Int): List<UserDbModel>
}