package com.uoa.telmaticsapp.domain.repository

import androidx.lifecycle.LiveData
import com.uoa.telmaticsapp.data.model.SensorsData
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SensorDataRepository {
    fun getSensorDataFromHardware(): Flow<List<SensorsData>>
    suspend fun stopSensor()
    fun getASensorData(sensorData: String):Flow<SensorsData>
    suspend fun saveSensorData(sensorData: SensorsData)
    suspend fun deleteSensorData(sensorData: SensorsData)
//    fun getLastSensorID(): LiveData<Long>

}