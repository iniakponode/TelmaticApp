package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import com.uoa.telmaticsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun RegisterUser():Resource<UserAPIResponse>
    suspend fun getUser():Resource<UserAPIResponse>
    suspend fun loginUser(loginQuery:String):Resource<UserAPIResponse>
    suspend fun updateUser(deviceModel: DeviceModel)
    suspend fun deleteUser(deviceModel:DeviceModel)
    suspend fun saveUser(deviceModel:DeviceModel)
    fun getUserFromDB(deviceModel: DeviceModel): Flow<List<DeviceModel>>

}