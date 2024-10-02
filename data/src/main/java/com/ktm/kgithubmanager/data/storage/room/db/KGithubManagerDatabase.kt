package com.ktm.kgithubmanager.data.storage.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ktm.kgithubmanager.data.storage.room.model.UserDbModel

@Database(entities = [UserDbModel::class], version = 1)
abstract class KGithubManagerDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}