package com.uoa.telmaticsapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.uoa.telmaticsapp.data.db.DataAccessObjects.*
import com.uoa.telmaticsapp.data.db.DDCAPDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTelmaticsDB(app: Application): DDCAPDB {
        return Room.databaseBuilder(app, DDCAPDB::class.java, "telmatic_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideSensorDataDAO(telmaticDB: DDCAPDB): SensorDataDAO{
        return telmaticDB.sensorDataDAO()
    }

    @Provides
    @Singleton
    fun providePointDAO(telmaticDB: DDCAPDB): PointDAO {
        return telmaticDB.pointDAO()
    }
    @Provides
    @Singleton
    fun provideLastKPointDAO(telmaticDB: DDCAPDB): LastKnownPointsDAO {
        return telmaticDB.lastKnownPointsDAO()
    }
    @Provides
    @Singleton
    fun provideTrackDAO(telmaticDB: DDCAPDB): TrackDAO {
        return telmaticDB.trackDAO()
    }

    @Provides
    @Singleton
    fun provideTripDetailsDAO(telmaticDB: DDCAPDB): TripDetailsDAO {
        return telmaticDB.tripDetailsDAO()
    }

    @Provides
    @Singleton
    fun provideUserDAO(telmaticDB: DDCAPDB): UserDAO {
        return telmaticDB.userDAO()
    }

}

@Qualifier
annotation class SensorDataDAOM

@Qualifier
annotation class PointDAOM

@Qualifier
annotation class TrackDAOM

@Qualifier
annotation class TripDetailsDAOM

@Qualifier
annotation class UserDAOM