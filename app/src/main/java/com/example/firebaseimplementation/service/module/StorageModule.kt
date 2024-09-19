package com.example.firebaseimplementation.service.module

import com.example.firebaseimplementation.service.AccountService
import com.example.firebaseimplementation.service.StorageService
import com.example.firebaseimplementation.service.impl.AccountServiceImpl
import com.example.firebaseimplementation.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

}