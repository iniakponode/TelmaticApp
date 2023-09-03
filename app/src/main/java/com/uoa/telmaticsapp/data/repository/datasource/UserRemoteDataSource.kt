package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.UserAPIResponse

interface UserRemoteDataSource {
    suspend fun registerUser(email:String?,phone:String?): UserAPIResponse
}