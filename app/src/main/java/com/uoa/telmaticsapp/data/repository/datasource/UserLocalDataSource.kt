package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun saveUser(user: User)
    fun getUsers():Flow<List<User>>
    suspend fun delete(user: User)
    suspend fun update(user: User)
}