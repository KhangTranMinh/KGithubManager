package com.ktm.kgithubmanager.presentation.di

import android.content.Context
import androidx.room.Room
import com.ktm.kgithubmanager.data.network.UserService
import com.ktm.kgithubmanager.data.storage.room.db.KGithubManagerDatabase
import com.ktm.kgithubmanager.data.storage.room.db.UserDao
import com.ktm.kgithubmanager.presentation.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        // for testing
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): KGithubManagerDatabase {
        return Room.databaseBuilder(
            context,
            KGithubManagerDatabase::class.java,
            "KGithubManagerDatabase"
        ).build()
    }

    @Provides
    fun provideUserDao(
        database: KGithubManagerDatabase
    ): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}