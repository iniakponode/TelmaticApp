package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uoa.telmaticsapp.data.model.LastKnownPoints
import kotlinx.coroutines.flow.Flow
import java.util.*
@Dao
interface LastKnownPointsDAO {
    @Insert
    suspend fun addLastKPoint(lastKpoint: LastKnownPoints)

    @Delete
    suspend fun deleteLastKPoint(lastKpoint: LastKnownPoints)

    @Query("Select * From lastKPoints")
    fun getAllLastKPoints(): List<LastKnownPoints>

    @Query("SELECT * FROM lastKPoints WHERE id =:lastkPid")
    fun getALastKPoint(lastkPid: UUID): Flow<LastKnownPoints>
}