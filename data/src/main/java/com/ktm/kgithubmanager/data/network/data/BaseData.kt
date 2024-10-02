package com.ktm.kgithubmanager.data.network.data

import com.ktm.kgithubmanager.data.network.ErrorCode

interface BaseRequest

interface BaseResponse {

    var errorCode: Int

    fun isSuccess() = ErrorCode.SUCCESS == errorCode
}