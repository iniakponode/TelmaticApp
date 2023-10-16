package com.uoa.telmaticsapp.presentation.di

import com.uoa.telmaticsapp.data.db.DataAccessObjects.DeviceDAO
import com.uoa.telmaticsapp.data.repository.UserRepoImpl
import com.uoa.telmaticsapp.data.repository.datasource.UserLocalDataSource
import com.uoa.telmaticsapp.data.repository.datasourceImpl.UserLocalDatasourceImpl
import com.uoa.telmaticsapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepoImpl(localUser: UserLocalDataSource): UserRepository {
        return UserRepoImpl(localUser)
    }
    @Provides
    @Singleton
    fun providelocalUser(deviceDAO: DeviceDAO): UserLocalDataSource {
        return UserLocalDatasourceImpl(deviceDAO)
    }
}