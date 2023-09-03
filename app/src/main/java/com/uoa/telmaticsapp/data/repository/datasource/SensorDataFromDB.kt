package com.uoa.telmaticsapp.data.repository.datasource

import androidx.lifecycle.LiveData
import com.uoa.telmaticsapp.data.model.SensorsData
import kotlinx.coroutines.flow.Flow

interface SensorDataFromDB {
    suspend fun saveSensorData(sensorData: SensorsData)
    fun getASavedSensorData(sensorData: String): Flow<SensorsData>
    fun getAllSavedSensorData():Flow<List<SensorsData>>
    suspend fun deleteSavedSensorData(sensorData: SensorsData)
//    fun getLastSensorID(): LiveData<Long>

}