package com.uoa.telmaticsapp.data.repository.datasourceImpl

import androidx.lifecycle.LiveData
import com.uoa.telmaticsapp.data.db.DataAccessObjects.SensorDataDAO
import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromDB
//import com.uoa.telmaticsapp.data.model.SensorsData
import kotlinx.coroutines.flow.Flow

class SensorDataFromDBImpl(private val sensorDataDAO: SensorDataDAO): SensorDataFromDB {
    override suspend fun saveSensorData(sensorData: SensorsData) {
        sensorDataDAO.insertSensorData(sensorData)
    }

    override fun getASavedSensorData(sensorData: String): Flow<SensorsData> {
        return sensorDataDAO.getASensorData(sensorData)
    }

    override fun getAllSavedSensorData(): Flow<List<SensorsData>> {
        return sensorDataDAO.getAllSensorData()
    }

    override suspend fun deleteSavedSensorData(sensorData: SensorsData) {
        sensorDataDAO.deleteSensorData(sensorData)
    }

//    override fun getLastSensorID(): LiveData<Long> {
//        return sensorDataDAO.getLastSensorID()
//    }

}