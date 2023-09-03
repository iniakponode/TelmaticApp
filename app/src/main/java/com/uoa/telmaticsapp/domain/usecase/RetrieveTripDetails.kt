package com.uoa.telmaticsapp.domain.usecase



import com.uoa.telmaticsapp.data.model.TripDetails
import com.uoa.telmaticsapp.domain.repository.TripDataRepo

class RetrieveTripDetails(private val tripdt: TripDataRepo){
    fun execute(tripDetails: TripDetails)=tripdt.getTripData(tripDetails)

}