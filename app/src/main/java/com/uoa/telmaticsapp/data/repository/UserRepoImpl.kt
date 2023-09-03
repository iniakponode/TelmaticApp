package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.User
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

    override suspend fun updateUser(user: User) {
        localUser.update(user)
    }

    override suspend fun deleteUser(user: User) {
        localUser.delete(user)
    }

    override suspend fun saveUser(user: User) {
        localUser.saveUser(user)
    }

    override fun getUserFromDB(user: User): Flow<List<User>> {
        return localUser.getUsers()
    }

}