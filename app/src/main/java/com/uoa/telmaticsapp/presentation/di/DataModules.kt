package com.uoa.telmaticsapp.presentation.di

import com.uoa.telmaticsapp.data.db.DataAccessObjects.LastKnownPointsDAO
import com.uoa.telmaticsapp.data.db.DataAccessObjects.PointDAO
import com.uoa.telmaticsapp.data.db.DataAccessObjects.TrackDAO
import com.uoa.telmaticsapp.data.repository.datasource.*
import com.uoa.telmaticsapp.data.repository.datasourceImpl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {
    @Provides
    @Singleton
    fun providePointData(pointDataDAO:PointDAO): PointData {
        return PointDataImpl(pointDataDAO)
    }

    @Provides
    @Singleton
    fun provideLastKPData(lastKPDataDAO:LastKnownPointsDAO): LastKnownPointsData {
        return LastKnownPointsDataImpl(lastKPDataDAO)
    }

    @Provides
    @Singleton
    fun provideComputedPointFromSensor(): ComputedPointFrmPhoneSensors{
        return ComputedPointFrmPhoneSensorsImpl()
    }

    @Provides
    @Singleton
    fun provideComputedTrackFromSensor(): ComputedTrackFrmPhoneSensors {
        return ComputedTrackFrmPhoneSensorsImpl()
    }

    @Provides
    @Singleton
    fun provideTrackData(trackDAO: TrackDAO): TrackData {
        return TrackImpl(trackDAO)
    }
}