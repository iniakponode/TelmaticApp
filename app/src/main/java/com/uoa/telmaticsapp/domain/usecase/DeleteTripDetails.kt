package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.TripDetails
import com.uoa.telmaticsapp.domain.repository.TripDataRepo

class DeleteTripDetails(private val tripD: TripDataRepo) {
    suspend fun execute(tripData: TripDetails)=tripD.deleteTripData(tripData)
}