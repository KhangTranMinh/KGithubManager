package com.ktm.kgithubmanager.data.network.data

import com.google.gson.annotations.SerializedName
import com.ktm.kgithubmanager.data.network.ErrorCode
import com.ktm.kgithubmanager.domain.entity.User

class FetchUsersDetailsRequest(
    @SerializedName("login_username") val loginUserName: String,
) : BaseRequest

class FetchUsersDetailsResponse(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("login") val login: String? = null,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
    @SerializedName("html_url") val htmlUrl: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("followers") val followers: Int? = null,
    @SerializedName("following") val following: Int? = null,
) : BaseResponse {

    override var errorCode: Int = ErrorCode.SUCCESS

    fun toUser(): User {
        return User(
            id = this.id ?: 0L,
            userName = this.login ?: "",
            avatarUrl = this.avatarUrl ?: "",
            blogUrl = this.htmlUrl ?: "",
            location = this.location ?: "",
            followers = this.followers ?: 0,
            following = this.following ?: 0
        )
    }
}