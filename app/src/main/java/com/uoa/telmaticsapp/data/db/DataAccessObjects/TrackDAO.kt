package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.room.*
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import kotlinx.coroutines.flow.Flow
@Dao
interface TrackDAO {
    @Insert
    suspend fun insertTrack(externalFactorsModel: ExternalFactorsModel)

    @Query("Select * From track")
     fun getAllTracks(): Flow<List<ExternalFactorsModel>>
    @Query("SELECT * FROM track WHERE trackId =:trackId")
    fun getATrack(trackId: String): Flow<ExternalFactorsModel>
    @Delete
    suspend fun deleteTrack(externalFactorsModel: ExternalFactorsModel)

//    @Query("UPDATE externalFactorsModel SET endDate= :endDate, drunk_state = :drunk_state, other_experiences= :other_experiences, overloading= :overloading WHERE trackId =:tripId")
//    fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String)
    @Update
    suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel)

}