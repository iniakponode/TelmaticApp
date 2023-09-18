package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow

interface SensorDataRepository {
    fun getSensorDataFromHardware(): Flow<List<SensorsModel>>
    suspend fun stopSensor()
    fun getASensorData(sensorData: String):Flow<SensorsModel>
    suspend fun saveSensorData(sensorData: SensorsModel)
    suspend fun deleteSensorData(sensorData: SensorsModel)
//    fun getLastSensorID(): LiveData<Long>

}