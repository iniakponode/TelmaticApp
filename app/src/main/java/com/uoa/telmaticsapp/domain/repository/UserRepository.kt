package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import com.uoa.telmaticsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun RegisterUser():Resource<UserAPIResponse>
    suspend fun getUser():Resource<UserAPIResponse>
    suspend fun loginUser(loginQuery:String):Resource<UserAPIResponse>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user:User)
    suspend fun saveUser(user:User)
    fun getUserFromDB(user: User): Flow<List<User>>

}