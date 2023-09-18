package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromDB
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromHardware
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import kotlinx.coroutines.flow.Flow

class SensorDataRepoImpl(
    private val sensorDataFromDB: SensorDataFromDB,
    private val sensorDataFromHardware: SensorDataFromHardware
): SensorDataRepository {
    override fun getSensorDataFromHardware(): Flow<List<SensorsModel>> {
        return sensorDataFromHardware.getSensorDataFromHardwre()
    }

    override suspend fun stopSensor() {
        sensorDataFromHardware.stopSensorData()
    }

    override fun getASensorData(sensorData: String): Flow<SensorsModel> {
        return sensorDataFromDB.getASavedSensorData(sensorData)
    }

    override suspend fun saveSensorData(sensorData: SensorsModel) {
        sensorDataFromDB.saveSensorData(sensorData)
    }

    override suspend fun deleteSensorData(sensorData: SensorsModel) {
        sensorDataFromDB.deleteSavedSensorData(sensorData)
    }

//    override fun getLastSensorID(): LiveData<Long> {
//        return sensorDataFromDB.getLastSensorID()
//    }

}