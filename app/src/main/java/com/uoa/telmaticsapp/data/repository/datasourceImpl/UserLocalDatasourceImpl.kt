package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.DeviceDAO
import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.data.repository.datasource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow

class UserLocalDatasourceImpl(private val deviceDAO: DeviceDAO): UserLocalDataSource {
    override suspend fun saveUser(deviceModel: DeviceModel) {
         deviceDAO.insertUser(deviceModel)
    }

    override fun getUsers(): Flow<List<DeviceModel>> {
        return deviceDAO.getAllUsers()
    }

    override suspend fun delete(deviceModel: DeviceModel) {
        deviceDAO.deleteUser(deviceModel)
    }

    override suspend fun update(deviceModel: DeviceModel) {
        deviceDAO.updateUser(deviceModel)
    }


}