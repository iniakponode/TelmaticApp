package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.UserDAO
import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.data.repository.datasource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow

class UserLocalDatasourceImpl(private val userDAO: UserDAO): UserLocalDataSource {
    override suspend fun saveUser(deviceModel: DeviceModel) {
         userDAO.insertUser(deviceModel)
    }

    override fun getUsers(): Flow<List<DeviceModel>> {
        return userDAO.getAllUsers()
    }

    override suspend fun delete(deviceModel: DeviceModel) {
        userDAO.deleteUser(deviceModel)
    }

    override suspend fun update(deviceModel: DeviceModel) {
        userDAO.updateUser(deviceModel)
    }


}