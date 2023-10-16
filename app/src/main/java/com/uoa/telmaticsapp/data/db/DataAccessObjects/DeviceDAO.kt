package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.*
import com.uoa.telmaticsapp.data.model.DeviceModel
import kotlinx.coroutines.flow.Flow
@Dao
interface DeviceDAO {

    @Insert
    suspend fun insertUser(deviceModel:DeviceModel)
    @Query("Select * FROM DeviceModel")
    fun getAllUsers(): Flow<List<DeviceModel>>

    @Query("Select * FROM DeviceModel Where clientId=:userid")
    fun getAUser(userid:String):Flow<DeviceModel>

    @Delete
    suspend fun deleteUser(deviceModel: DeviceModel)

    @Update
    suspend fun updateUser(deviceModel: DeviceModel)



}