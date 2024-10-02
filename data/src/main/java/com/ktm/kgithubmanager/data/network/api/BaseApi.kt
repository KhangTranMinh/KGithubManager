package com.ktm.kgithubmanager.data.network.api

import com.ktm.kgithubmanager.data.network.data.BaseRequest
import com.ktm.kgithubmanager.data.network.data.BaseResponse


abstract class BaseApi<Request : BaseRequest, Response : BaseResponse> {

    /**
     * Call this function to perform API request
     */
    abstract suspend fun execute(request: Request): Response?
}