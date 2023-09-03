package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.UserDAO
import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.data.repository.datasource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow

class UserLocalDatasourceImpl(private val userDAO: UserDAO): UserLocalDataSource {
    override suspend fun saveUser(user: User) {
         userDAO.insertUser(user)
    }

    override fun getUsers(): Flow<List<User>> {
        return userDAO.getAllUsers()
    }

    override suspend fun delete(user: User) {
        userDAO.deleteUser(user)
    }

    override suspend fun update(user: User) {
        userDAO.updateUser(user)
    }


}