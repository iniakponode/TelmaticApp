package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.TripDataRepo

class RetrieveTripDetailsByDate(private val tripD: TripDataRepo) {
    fun execute(date: String)=tripD.getTripsDataByDate(date)
}