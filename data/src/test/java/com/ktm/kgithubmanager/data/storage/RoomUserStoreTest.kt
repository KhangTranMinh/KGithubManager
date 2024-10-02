package com.ktm.kgithubmanager.data.storage

import com.ktm.kgithubmanager.data.storage.room.RoomUserStore
import com.ktm.kgithubmanager.data.storage.room.db.UserDao
import com.ktm.kgithubmanager.data.storage.room.model.UserDbModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyLong

class RoomUserStoreTest {

    @MockK
    lateinit var userDao: UserDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getUsers_Response_Empty() {
        runTest {
            // mock
            coEvery {
                userDao.getUsers(anyLong(), anyInt())
            } returns emptyList()

            val result = RoomUserStore(userDao).getUsers(anyLong(), anyInt())
            assertEquals(0, result.size)
        }
    }

    @Test
    fun getUsers_Response_NotEmpty() {
        runTest {
            // mock
            coEvery {
                userDao.getUsers(anyLong(), anyInt())
            } returns arrayListOf(
                UserDbModel(id = 1L)
            )

            val result = RoomUserStore(userDao).getUsers(anyLong(), anyInt())
            assertEquals(1L, result[0].id)
        }
    }

    @Test
    fun saveUser() {
        runTest {
            // mock
            every { userDao.insertAll() } returns Unit

            RoomUserStore(userDao).saveUsers(anyList())
            verify(exactly = 1) { userDao.insertAll() }
        }
    }
}