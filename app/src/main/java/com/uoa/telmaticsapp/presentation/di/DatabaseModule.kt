package com.uoa.telmaticsapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.uoa.telmaticsapp.data.db.DataAccessObjects.*
import com.uoa.telmaticsapp.data.db.PhDSafeDriveDB
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
    fun provideTelmaticsDB(app: Application): PhDSafeDriveDB {
        return Room.databaseBuilder(app, PhDSafeDriveDB::class.java, "telmatic_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideSensorDataDAO(telmaticDB: PhDSafeDriveDB): SensorDataDAO{
        return telmaticDB.sensorDataDAO()
    }

    @Provides
    @Singleton
    fun providePointDAO(telmaticDB: PhDSafeDriveDB): PointDAO {
        return telmaticDB.pointDAO()
    }
    @Provides
    @Singleton
    fun provideLastKPointDAO(telmaticDB: PhDSafeDriveDB): LastKnownPointsDAO {
        return telmaticDB.lastKnownPointsDAO()
    }
    @Provides
    @Singleton
    fun provideTrackDAO(telmaticDB: PhDSafeDriveDB): TrackDAO {
        return telmaticDB.trackDAO()
    }

    @Provides
    @Singleton
    fun provideTripDetailsDAO(telmaticDB: PhDSafeDriveDB): TripDetailsDAO {
        return telmaticDB.tripDetailsDAO()
    }

    @Provides
    @Singleton
    fun provideUserDAO(telmaticDB: PhDSafeDriveDB): UserDAO {
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