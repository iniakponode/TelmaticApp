package com.uoa.telmaticsapp.presentation.di

import com.uoa.telmaticsapp.data.repository.LastKnowPointsRepoImpl
import com.uoa.telmaticsapp.data.repository.PointRepoImpl
import com.uoa.telmaticsapp.data.repository.SensorDataRepoImpl
import com.uoa.telmaticsapp.data.repository.TrackRepoImpl
import com.uoa.telmaticsapp.data.repository.datasource.*
import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo
import com.uoa.telmaticsapp.domain.repository.PointRepository
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import com.uoa.telmaticsapp.domain.repository.TrackRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModules {
    @Provides
    @Singleton
    fun provideSensorDataRep(sensorDataFromDB: SensorDataFromDB, sensorDataFromHardware: SensorDataFromHardware): SensorDataRepository {
        return SensorDataRepoImpl(sensorDataFromDB,sensorDataFromHardware)
    }

    @Provides
    @Singleton
    fun providePointRepository(pointData: PointData,computedPointFrmPhoneSensors: ComputedPointFrmPhoneSensors): PointRepository {
        return PointRepoImpl(pointData,computedPointFrmPhoneSensors)
    }

    @Provides
    @Singleton
    fun provideLastKPRepository(lastKPData:LastKnownPointsData): LastKnownPointsRepo {
        return LastKnowPointsRepoImpl(lastKPData)
    }

    @Provides
    @Singleton
    fun provideTrackRepository(trackData: TrackData,
                               computedTrackFrmPhoneSensors: ComputedTrackFrmPhoneSensors): TrackRepository {
        return TrackRepoImpl(trackData,computedTrackFrmPhoneSensors)
    }
}