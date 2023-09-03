package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uoa.telmaticsapp.data.model.SensorsData
import kotlinx.coroutines.flow.Flow
@Dao
interface SensorDataDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSensorData(sensorData: SensorsData)

    @Query("SELECT * FROM sensor_data WHERE sensorDataID =:sensorDataId")
    fun getASensorData(sensorDataId: String): Flow<SensorsData>

    @Query("Select * From sensor_data")
    fun getAllSensorData(): Flow<List<SensorsData>>

    @Delete
    suspend fun deleteSensorData(sensorData: SensorsData)

//    @Query("SELECT sensorDataID FROM sensor_data ORDER BY sensorDataID DESC LIMIT 1")
//    fun getLastSensorID(): LiveData<Long>
}