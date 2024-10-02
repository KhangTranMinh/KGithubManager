package com.ktm.kgithubmanager.presentation.di

import com.ktm.kgithubmanager.data.repository.UserRepositoryImpl
import com.ktm.kgithubmanager.data.storage.room.RoomUserStore
import com.ktm.kgithubmanager.data.storage.UserStore
import com.ktm.kgithubmanager.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindUserStore(impl: RoomUserStore): UserStore

    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}