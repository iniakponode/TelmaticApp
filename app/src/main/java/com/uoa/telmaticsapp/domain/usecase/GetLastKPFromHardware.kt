package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo

class GetLastKPFromHardware(private val lastKPRepo:LastKnownPointsRepo) {
    fun execute(
//        accuracy: String,
        lastKPId:String,
        lastTrackId: String,
        latitude: Double,
        longitude: Double,
        pointDate: String,
        startTrackId: String
    )=lastKPRepo.computeLastKnownPointsFromHardware(
//        accuracy,
        lastKPId,
        lastTrackId,
        latitude,
        longitude,
        pointDate,
        startTrackId
    )
}