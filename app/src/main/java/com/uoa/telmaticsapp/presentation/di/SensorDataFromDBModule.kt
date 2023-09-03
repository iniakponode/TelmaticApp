package com.uoa.telmaticsapp.presentation.di

import com.uoa.telmaticsapp.data.db.DataAccessObjects.SensorDataDAO
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromDB
import com.uoa.telmaticsapp.data.repository.datasourceImpl.SensorDataFromDBImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorDataFromDBModule {
    @Provides
    @Singleton
    fun provideSensorDataFromDB(sensorDataDAO: SensorDataDAO): SensorDataFromDB {
        return SensorDataFromDBImpl(sensorDataDAO)
    }
}