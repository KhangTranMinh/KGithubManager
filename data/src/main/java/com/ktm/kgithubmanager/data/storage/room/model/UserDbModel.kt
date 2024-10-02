package com.ktm.kgithubmanager.data.storage.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ktm.kgithubmanager.domain.entity.User

@Entity
data class UserDbModel(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "userName") val userName: String = "",
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String = "",
    @ColumnInfo(name = "blogUrl") val blogUrl: String = "",
    @ColumnInfo(name = "location") val location: String = "",
    @ColumnInfo(name = "followers") val followers: Int = 0,
    @ColumnInfo(name = "following") val following: Int = 0,
) {

    fun toUser(): User {
        return User(
            id = this.id,
            userName = this.userName,
            avatarUrl = this.avatarUrl,
            blogUrl = this.blogUrl,
            location = this.location,
            followers = this.followers,
            following = this.following,
        )
    }

    companion object {
        fun fromUser(user: User): UserDbModel {
            return UserDbModel(
                id = user.id,
                userName = user.userName,
                avatarUrl = user.avatarUrl,
                blogUrl = user.blogUrl,
                location = user.location,
                followers = user.followers,
                following = user.following,
            )
        }
    }
}