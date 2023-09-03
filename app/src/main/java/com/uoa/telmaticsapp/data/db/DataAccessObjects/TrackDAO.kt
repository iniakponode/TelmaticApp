package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.*
import com.uoa.telmaticsapp.data.model.Track
import kotlinx.coroutines.flow.Flow
@Dao
interface TrackDAO {
    @Insert
    suspend fun insertTrack(track: Track)

    @Query("Select * From track")
     fun getAllTracks(): Flow<List<Track>>
    @Query("SELECT * FROM track WHERE trackId =:trackId")
    fun getATrack(trackId: String): Flow<Track>
    @Delete
    suspend fun deleteTrack(track: Track)

//    @Query("UPDATE track SET endDate= :endDate, drunk_state = :drunk_state, other_experiences= :other_experiences, overloading= :overloading WHERE trackId =:tripId")
//    fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String)
    @Update
    suspend fun updateTrack(track: Track)

}