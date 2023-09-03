package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uoa.telmaticsapp.data.model.TrackPoint
import kotlinx.coroutines.flow.Flow
@Dao
interface PointDAO {
    @Insert
    suspend fun insertPoint(trackPoint: TrackPoint)

    @Delete
    suspend fun deletePoint(trackPoint: TrackPoint)

    @Query("Select * From trackpoint")
     fun getAllPoints(): Flow<List<TrackPoint>>

    @Query("SELECT * FROM trackpoint WHERE pointID =:pointId")
    fun getAPoint(pointId: String): Flow<TrackPoint>

}