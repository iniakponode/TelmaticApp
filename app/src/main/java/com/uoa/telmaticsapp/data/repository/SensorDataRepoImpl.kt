package com.uoa.telmaticsapp.data.repository

import androidx.lifecycle.LiveData
import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromDB
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromHardware
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import kotlinx.coroutines.flow.Flow
import java.util.*

class SensorDataRepoImpl(
    private val sensorDataFromDB: SensorDataFromDB,
    private val sensorDataFromHardware: SensorDataFromHardware
): SensorDataRepository {
    override fun getSensorDataFromHardware(): Flow<List<SensorsData>> {
        return sensorDataFromHardware.getSensorDataFromHardwre()
    }

    override suspend fun stopSensor() {
        sensorDataFromHardware.stopSensorData()
    }

    override fun getASensorData(sensorData: String): Flow<SensorsData> {
        return sensorDataFromDB.getASavedSensorData(sensorData)
    }

    override suspend fun saveSensorData(sensorData: SensorsData) {
        sensorDataFromDB.saveSensorData(sensorData)
    }

    override suspend fun deleteSensorData(sensorData: SensorsData) {
        sensorDataFromDB.deleteSavedSensorData(sensorData)
    }

//    override fun getLastSensorID(): LiveData<Long> {
//        return sensorDataFromDB.getLastSensorID()
//    }

}