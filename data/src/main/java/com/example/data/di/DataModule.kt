package com.example.data.di

import com.example.data.auth.AuthTokenStorage
import com.example.data.auth.TokenProvider
import com.example.data.user.remote.UserApiServiceImpl
import com.example.data.user.remote.api.UserApiService
import com.example.data.user.repository.UserRepositoryImpl
import com.example.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): IUserRepository

    @Binds
    @Singleton
    abstract fun bindUserApiService(impl: UserApiServiceImpl): UserApiService

    @Binds
    @Singleton
    abstract fun bindTokenProvider(impl: AuthTokenStorage): TokenProvider
}
