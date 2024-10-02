package com.ktm.kgithubmanager.data.network.api

import com.ktm.kgithubmanager.data.network.UserService
import com.ktm.kgithubmanager.data.network.data.FetchUsersRequest
import com.ktm.kgithubmanager.data.network.data.FetchUsersResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyLong

class FetchUserApiTest {

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
            coEvery { userService.fetchUsers(any(), any()) } returns null

            val result = FetchUsersApi(userService).execute(
                FetchUsersRequest(anyLong(), anyInt())
            )
            assertEquals(null, result)
        }
    }

    @Test
    fun execute_Response_Success() {
        runTest {
            // mock
            coEvery {
                userService.fetchUsers(any(), any())
            } returns FetchUsersResponse().apply {
                add(
                    FetchUsersResponse.UserResponse(
                        id = 1L
                    )
                )
            }

            val result = FetchUsersApi(userService).execute(
                FetchUsersRequest(anyLong(), anyInt())
            )
            assertEquals(1L, result?.get(0)?.id)
        }
    }
}