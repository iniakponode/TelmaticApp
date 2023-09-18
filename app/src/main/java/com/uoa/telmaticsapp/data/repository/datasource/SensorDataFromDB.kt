package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow

interface SensorDataFromDB {
    suspend fun saveSensorData(sensorData: SensorsModel)
    fun getASavedSensorData(sensorData: String): Flow<SensorsModel>
    fun getAllSavedSensorData():Flow<List<SensorsModel>>
    suspend fun deleteSavedSensorData(sensorData: SensorsModel)
//    fun getLastSensorID(): LiveData<Long>

}