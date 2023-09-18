package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import com.uoa.telmaticsapp.data.repository.datasource.UserLocalDataSource
import com.uoa.telmaticsapp.data.util.Resource
import com.uoa.telmaticsapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepoImpl(private val localUser: UserLocalDataSource): UserRepository {
    override suspend fun RegisterUser(): Resource<UserAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): Resource<UserAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(loginQuery: String): Resource<UserAPIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(deviceModel: DeviceModel) {
        localUser.update(deviceModel)
    }

    override suspend fun deleteUser(deviceModel: DeviceModel) {
        localUser.delete(deviceModel)
    }

    override suspend fun saveUser(deviceModel: DeviceModel) {
        localUser.saveUser(deviceModel)
    }

    override fun getUserFromDB(deviceModel: DeviceModel): Flow<List<DeviceModel>> {
        return localUser.getUsers()
    }

}