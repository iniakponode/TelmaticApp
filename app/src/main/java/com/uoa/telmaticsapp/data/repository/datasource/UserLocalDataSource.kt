package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.DeviceModel
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUser(deviceModel: DeviceModel)
    fun getUsers():Flow<List<DeviceModel>>
    suspend fun delete(deviceModel: DeviceModel)
    suspend fun update(deviceModel: DeviceModel)
}