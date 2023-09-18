package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.*
import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow
@Dao
interface SensorDataDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSensorData(sensorData: SensorsModel)

    @Query("SELECT * FROM sensor_data WHERE sensorDataID =:sensorDataId")
    fun getASensorData(sensorDataId: String): Flow<SensorsModel>

    @Query("Select * From sensor_data")
    fun getAllSensorData(): Flow<List<SensorsModel>>

    @Delete
    suspend fun deleteSensorData(sensorData: SensorsModel)

//    @Query("SELECT sensorDataID FROM sensor_data ORDER BY sensorDataID DESC LIMIT 1")
//    fun getLastSensorID(): LiveData<Long>
}