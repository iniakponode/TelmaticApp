package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.TripDetails
import com.uoa.telmaticsapp.domain.repository.TripDataRepo

class SaveTripDetails(private val tripDataRepo: TripDataRepo) {
    suspend fun execute(tripD: TripDetails)=tripDataRepo.saveTripData(tripD)
}