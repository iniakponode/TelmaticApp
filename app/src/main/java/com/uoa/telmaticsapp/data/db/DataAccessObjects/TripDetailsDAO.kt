package com.uoa.telmaticsapp.data.db.DataAccessObjects

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uoa.telmaticsapp.data.model.TripDetails
import kotlinx.coroutines.flow.Flow
@Dao
interface TripDetailsDAO {
    @Insert
    suspend fun insertTripDetails(tripDet: TripDetails)

    @Delete
    suspend fun deleteTripDetails(tripDet: TripDetails)

    @Query("Select * From trip_details")
    fun getAllTripDetails():Flow<List<TripDetails>>

    @Query("SELECT * FROM trip_details WHERE trackId =:tripDID")
    fun getATripDetail(tripDID: String): Flow<TripDetails>

}