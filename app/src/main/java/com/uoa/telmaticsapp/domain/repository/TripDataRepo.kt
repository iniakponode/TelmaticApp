package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.TripDetails
import kotlinx.coroutines.flow.Flow

interface TripDataRepo {
    suspend fun saveTripData(tripDetails: TripDetails)
    fun getTripsDataByDate(date:String): Flow<List<TripDetails>>
    fun getTripData(tripDetails: TripDetails): Flow<List<TripDetails>>
    suspend fun deleteTripData(tripData: TripDetails)
}