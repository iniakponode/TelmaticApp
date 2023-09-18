package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.SensorDataDAO
import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromDB
//import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow

class SensorDataFromDBImpl(private val sensorDataDAO: SensorDataDAO): SensorDataFromDB {
    override suspend fun saveSensorData(sensorData: SensorsModel) {
        sensorDataDAO.insertSensorData(sensorData)
    }

    override fun getASavedSensorData(sensorData: String): Flow<SensorsModel> {
        return sensorDataDAO.getASensorData(sensorData)
    }

    override fun getAllSavedSensorData(): Flow<List<SensorsModel>> {
        return sensorDataDAO.getAllSensorData()
    }

    override suspend fun deleteSavedSensorData(sensorData: SensorsModel) {
        sensorDataDAO.deleteSensorData(sensorData)
    }

//    override fun getLastSensorID(): LiveData<Long> {
//        return sensorDataDAO.getLastSensorID()
//    }

}