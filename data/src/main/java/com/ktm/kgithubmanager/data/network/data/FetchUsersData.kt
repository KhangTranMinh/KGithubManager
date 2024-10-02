package com.ktm.kgithubmanager.data.network.data

import com.google.gson.annotations.SerializedName
import com.ktm.kgithubmanager.data.network.ErrorCode
import com.ktm.kgithubmanager.domain.entity.User

class FetchUsersRequest(
    @SerializedName("since") val since: Long,
    @SerializedName("per_page") val perPage: Int,
) : BaseRequest

class FetchUsersResponse : ArrayList<FetchUsersResponse.UserResponse>(), BaseResponse {

    override var errorCode: Int = ErrorCode.SUCCESS

    class UserResponse(
        @SerializedName("id") val id: Long? = null,
        @SerializedName("login") val login: String? = null,
        @SerializedName("avatar_url") val avatarUrl: String? = null,
        @SerializedName("html_url") val htmlUrl: String? = null,
    ) {

        fun toUser(): User {
            return User(
                id = this.id ?: 0L,
                userName = this.login ?: "",
                avatarUrl = this.avatarUrl ?: "",
                blogUrl = this.htmlUrl ?: ""
            )
        }
    }
}