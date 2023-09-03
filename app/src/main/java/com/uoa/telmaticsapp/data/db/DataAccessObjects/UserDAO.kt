package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uoa.telmaticsapp.data.model.User
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user:User)
    @Query("Select * FROM User")
    fun getAllUsers(): Flow<List<User>>

    @Query("Select * FROM User Where clientId=:userid")
    fun getAUser(userid:String):Flow<User>

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)



}