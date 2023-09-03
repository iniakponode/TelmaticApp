package com.uoa.telmaticsapp.presentation.di

import com.uoa.telmaticsapp.domain.repository.*
import com.uoa.telmaticsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModules {
    @Provides
    @Singleton
    fun provideStopSensors(sensorRepoData:SensorDataRepository): StopSensors{
        return StopSensors(sensorRepoData)
    }

    @Provides
    @Singleton
    fun provideStartSensors(sensorRepoData:SensorDataRepository): StartSensors {
        return StartSensors(sensorRepoData)
    }
    @Provides
    @Singleton
    fun provideDeleteSensors(sensorRepoData:SensorDataRepository): DeleteSensorData {
        return DeleteSensorData(sensorRepoData)
    }

    @Provides
    @Singleton
    fun provideAddPoint(pointRepo: PointRepository): AddPoint {
        return AddPoint(pointRepo)
    }
    @Provides
    @Singleton
    fun provideAddLastKP(lastKPRepo: LastKnownPointsRepo): AddLastKnownPoints {
        return AddLastKnownPoints(lastKPRepo)
    }
    @Provides
    @Singleton
    fun provideAddTrack(trackRepo: TrackRepository): AddTrack {
        return AddTrack(trackRepo)
    }

    @Provides
    @Singleton
    fun provideUpdateTrack(trackRepo:TrackRepository): UpdateTrack{
        return UpdateTrack(trackRepo)
    }

    @Provides
    @Singleton
    fun provideGetComputedTrack(trackRepo: TrackRepository):GetComputedTrack {
        return GetComputedTrack(trackRepo)
    }

    @Provides
    @Singleton
    fun provideGetPointFromHardware(pointRepo: PointRepository):GetPointsFromHardware {
        return GetPointsFromHardware(pointRepo)
    }
    @Provides
    @Singleton
    fun provideGetLaskPoint(lastKPRepo: LastKnownPointsRepo):GetLastKPFromHardware {
        return GetLastKPFromHardware(lastKPRepo)
    }
    @Provides
    @Singleton
    fun provideGetLaskPointFrmDB(lastKPRepo: LastKnownPointsRepo):GetLasKPoints {
        return GetLasKPoints(lastKPRepo)
    }

    @Provides
    @Singleton
    fun provideCreateUser(userRepo:UserRepository):CreateUser {
        return CreateUser(userRepo)
    }

    @Provides
    @Singleton
    fun provideUpdateUser(userRepo:UserRepository):UpdateUser {
        return UpdateUser(userRepo)
    }
}