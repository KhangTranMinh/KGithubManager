package com.ktm.kgithubmanager.data.network.api

import com.ktm.kgithubmanager.data.network.UserService
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsRequest
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class FetchUserDetailsApiTest {

    @MockK
    lateinit var userService: UserService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun execute_Response_Null() {
        runTest {
            // mock
            coEvery { userService.fetchUserDetails(any()) } returns null

            val result = FetchUserDetailsApi(userService).execute(
                FetchUsersDetailsRequest(anyString())
            )
            assertEquals(null, result)
        }
    }

    @Test
    fun execute_Response_Success() {
        runTest {
            // mock
            coEvery {
                userService.fetchUserDetails(any())
            } returns FetchUsersDetailsResponse(id = 1L)

            val result = FetchUserDetailsApi(userService).execute(
                FetchUsersDetailsRequest(anyString())
            )
            assertEquals(1L, result?.id)
        }
    }
}