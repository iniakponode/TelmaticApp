package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow

interface SensorDataFromHardware {
    fun getSensorDataFromHardwre(): Flow<List<SensorsModel>>
    suspend fun stopSensorData()
//    suspend fun saveSensorData(sensorData: SensorsModel)
//    suspend fun deleteSensorData(sensorData: SensorsModel)
}