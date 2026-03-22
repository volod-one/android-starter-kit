package com.example.data.di

import com.example.core.database.DatabaseFactory
import com.example.data.user.local.UserDatabase
import com.example.data.user.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UserDatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(
        factory: DatabaseFactory,
    ): UserDatabase {
        return factory.create(
            UserDatabase::class.java,
            "user_database.db"
        )
    }

    @Provides
    fun provideUserDao(db: UserDatabase): UserDao = db.userDao()
}