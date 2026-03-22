package com.example.core.datastore.di

import com.example.core.datastore.DataStoreFactory
import com.example.core.datastore.DataStoreFactoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataStoreCoreModule {

    @Binds
    @Singleton
    abstract fun bindDataStoreFactory(
        impl: DataStoreFactoryImpl,
    ): DataStoreFactory
}