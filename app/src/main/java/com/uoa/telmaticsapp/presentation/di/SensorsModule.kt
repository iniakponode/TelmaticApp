package com.uoa.telmaticsapp.presentation.di

import android.app.Application
import com.uoa.telmaticsapp.data.repository.datasource.*
import com.uoa.telmaticsapp.data.repository.datasourceImpl.SensorDataFromHardwareImpl
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import com.uoa.telmaticsapp.domain.usecase.SaveSensorData
import com.uoa.telmaticsapp.util.TrackingSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorsModule {
    @Provides
    @Singleton
    @AccelerometerSensorM
    fun provideAccelerometerSensor(app: Application): TrackingSensor{
        return AccelerometerSensor(app)
    }

    @Provides
    @Singleton
    @AccelerometerSensorLtdAM
    fun provideAccelerometerSensorLtdA(app: Application): TrackingSensor{
        return AccelerometerSensorLtdA(app)
    }

    @Provides
    @Singleton
    @AccelerometerSensorLtdAUnCalibratedM
    fun provideAccelerometerSensorLtdAUnCalibrated(app: Application): TrackingSensor{
        return AccelerometerSensorLtdAUnCalibrated(app)
    }

    @Provides
    @Singleton
    @GyroscopeSensorM
    fun provideGyroscopeSensor(app: Application): TrackingSensor{
        return GyroscopeSensor(app)
    }
    @Provides
    @Singleton
    @GravitySensorM
    fun provideGravitySensor(app: Application): TrackingSensor{
        return GravitySensor(app)
    }
    @Provides
    @Singleton
    @GyroscopeSensorLtdAM
    fun provideGyroscopeSensorLtdA(app: Application): TrackingSensor{
        return GyroscopeSensorLtdA(app)
    }

    @Provides
    @Singleton
    @GyroscopeSensorLtdAUnCalibratedM
    fun provideGyroscopeSensorLtdAUnCalibrated(app: Application): TrackingSensor{
        return GyroscopeSensorLtdAUncalibrated(app)
    }

    @Provides
    @Singleton
    @GPSLocationSensorM
    fun provideGPSLocationSensor(app: Application): TrackingSensor{
        return GPSLocationSensor(app)
    }

//    @Provides
//    @Singleton
//    fun provideLocationSensor(app:TelmaticApp): TrackingSensor{
//        return LocationSensor(app)
//    }

    @Provides
    @Singleton
    @LightSensorM
    fun provideLightSensor(app: Application): TrackingSensor{
        return LightSensor(app)
    }

    @Provides
    @Singleton
    @LinearAccelerationM
    fun provideLinearAcceleration(app: Application): TrackingSensor{
        return LinearAcceleration(app)
    }

    @Provides
    @Singleton
    @ProximitySensorM
    fun provideProximitySensor(app: Application): TrackingSensor{
        return ProximitySensor(app)
    }

    @Provides
    @Singleton
    @RotationVectorSensorM
    fun provideRotationVectorSensor(app: Application): TrackingSensor{
        return RotationVectorSensor(app)
    }

    @Provides
    @Singleton
    @MagnetometerSensorM
    fun provideMagnetometerSensor(app: Application): TrackingSensor{
        return RotationVectorSensor(app)
    }

    @Provides
    @Singleton
    fun provideSaveSensorData(sensorDataRepo: SensorDataRepository): SaveSensorData{
        return SaveSensorData(sensorDataRepo)
    }

    @Provides
    @Singleton
    fun provideSensorsDataFromHardware(
        @AccelerometerSensorM acceleromSensorM: TrackingSensor,
        @AccelerometerSensorLtdAM acceleromSensorLtdAM: TrackingSensor,
        @AccelerometerSensorLtdAUnCalibratedM accelerometerSensorLtdAUnCalibratedM: TrackingSensor,
        @GPSLocationSensorM gpsLocationSensorM: TrackingSensor,
        @GyroscopeSensorM gyroscopeSensorM: TrackingSensor,
        @GyroscopeSensorLtdAM gyroscopeSensorLtdAM: TrackingSensor,
        @GyroscopeSensorLtdAUnCalibratedM gyroscopeSensorLtdAUncalibrated: TrackingSensor,
        @LinearAccelerationM linearAccelerationM: TrackingSensor,
        @ProximitySensorM proximitySensorM: TrackingSensor,
        @RotationVectorSensorM rotationVectorSensor: TrackingSensor,
        @MagnetometerSensorM magnetometerSensor: TrackingSensor,
        @GravitySensorM gravitySensor: TrackingSensor
        ): SensorDataFromHardware{
        return SensorDataFromHardwareImpl(
            acceleromSensorM,
            accelerometerSensorLtdAUnCalibratedM,
//            acceleromSensorLtdAM,
            gpsLocationSensorM,
            gyroscopeSensorM,
//            gyroscopeSensorLtdAM,
            gyroscopeSensorLtdAUncalibrated,
            linearAccelerationM,
            rotationVectorSensor,
            magnetometerSensor,
            gravitySensor

            )
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LightSensorM

@Qualifier
annotation class RotationVectorSensorM

@Qualifier
annotation class MagnetometerSensorM
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AccelerometerSensorM


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GyroscopeSensorM

@Qualifier
annotation class GyroscopeSensorLtdAM

@Qualifier
annotation class GyroscopeSensorLtdAUnCalibratedM

@Qualifier
annotation class GravitySensorM

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GPSLocationSensorM

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LinearAccelerationM

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProximitySensorM

@Qualifier
annotation class AccelerometerSensorLtdAUnCalibratedM

@Qualifier
annotation class AccelerometerSensorLtdAM