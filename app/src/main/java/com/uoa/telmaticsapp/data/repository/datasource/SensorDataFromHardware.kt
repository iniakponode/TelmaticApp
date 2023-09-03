package com.uoa.telmaticsapp.data.repository.datasource

import android.location.LocationListener
import com.uoa.telmaticsapp.data.model.SensorsData
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SensorDataFromHardware {
    fun getSensorDataFromHardwre(): Flow<List<SensorsData>>
    suspend fun stopSensorData()
//    suspend fun saveSensorData(sensorData: SensorsData)
//    suspend fun deleteSensorData(sensorData: SensorsData)
}