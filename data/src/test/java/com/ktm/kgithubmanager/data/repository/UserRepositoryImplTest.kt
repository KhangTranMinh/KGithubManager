package com.ktm.kgithubmanager.data.repository

import com.ktm.kgithubmanager.data.network.ErrorCode
import com.ktm.kgithubmanager.data.network.api.FetchUserDetailsApi
import com.ktm.kgithubmanager.data.network.api.FetchUsersApi
import com.ktm.kgithubmanager.data.network.data.FetchUsersDetailsResponse
import com.ktm.kgithubmanager.data.storage.UserStore
import com.ktm.kgithubmanager.domain.entity.User
import com.ktm.kgithubmanager.domain.repository.result.Error
import com.ktm.kgithubmanager.domain.repository.result.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString

class UserRepositoryImplTest {

    @MockK
    lateinit var fetchUserApi: FetchUsersApi

    @MockK
    lateinit var fetchUserDetailsApi: FetchUserDetailsApi

    @MockK
    lateinit var userStore: UserStore

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun fetchUserDetails_Response_Null() {
        runTest {
            // mock
            coEvery { fetchUserDetailsApi.execute(any()) } returns null

            val result = UserRepositoryImpl(
                fetchUserApi,
                fetchUserDetailsApi,
                userStore
            ).fetchUserDetails(anyString())
            when (result) {
                is Result.Error -> {
                    val error = result.error
                    when (error) {
                        is Error.ApiError -> fail()
                        is Error.GeneralError -> assertTrue(true)
                    }
                }

                is Result.Success -> fail()
            }
        }
    }

    @Test
    fun fetchUserDetails_Response_NotSuccess() {
        runTest {
            // mock
            coEvery {
                fetchUserDetailsApi.execute(any())
            } returns FetchUsersDetailsResponse().apply {
                errorCode = ErrorCode.UNKNOWN
            }

            val result = UserRepositoryImpl(
                fetchUserApi,
                fetchUserDetailsApi,
                userStore
            ).fetchUserDetails(anyString())
            when (result) {
                is Result.Error -> {
                    val error = result.error
                    when (error) {
                        is Error.ApiError -> assertTrue(true)
                        is Error.GeneralError -> fail()
                    }
                }

                is Result.Success -> fail()
            }
        }
    }

    @Test
    fun fetchUserDetails_Response_Success() {
        runTest {
            // mock
            coEvery {
                fetchUserDetailsApi.execute(any())
            } returns FetchUsersDetailsResponse(id = 1L)

            val result = UserRepositoryImpl(
                fetchUserApi,
                fetchUserDetailsApi,
                userStore
            ).fetchUserDetails(anyString())
            when (result) {
                is Result.Error -> {
                    val error = result.error
                    when (error) {
                        is Error.ApiError -> fail()
                        is Error.GeneralError -> fail()
                    }
                }

                is Result.Success -> {
                    assertEquals(1L, result.data.id)
                }
            }
        }
    }

    @Test
    fun fetchUsers_Database_Empty() {
        runTest {
            // mock
            val mockUserRepositoryImpl = spyk(
                objToCopy = UserRepositoryImpl(
                    fetchUserApi, fetchUserDetailsApi, userStore
                ),
                recordPrivateCalls = true
            )
            coEvery {
                mockUserRepositoryImpl["fetchRemote"](anyLong(), anyInt())
            } returns Result.Success(Pair(listOf<User>(), 0L))

            coEvery {
                userStore.getUsers(anyLong(), anyInt())
            } returns emptyList()

            mockUserRepositoryImpl.fetchUsers(anyLong(), anyInt())
            verify(exactly = 1) { mockUserRepositoryImpl["fetchRemote"](anyLong(), anyInt()) }
        }
    }

    @Test
    fun fetchUsers_Database_NotEmpty() {
        runTest {
            // mock
            val mockUserRepositoryImpl = spyk(
                objToCopy = UserRepositoryImpl(
                    fetchUserApi, fetchUserDetailsApi, userStore
                ),
                recordPrivateCalls = true
            )
            coEvery {
                mockUserRepositoryImpl["fetchRemote"](anyLong(), anyInt())
            } returns Result.Success(Pair(listOf<User>(), 0L))

            coEvery {
                userStore.getUsers(anyLong(), anyInt())
            } returns listOf(User(id = 1L))

            mockUserRepositoryImpl.fetchUsers(anyLong(), anyInt())
            verify(exactly = 0) { mockUserRepositoryImpl["fetchRemote"](anyLong(), anyInt()) }
        }
    }
}