package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo

class AddLastKnownPoints(private val lastKPointsRepo: LastKnownPointsRepo) {
    suspend fun execute(lkPoints:LastKnownPoints){
        lastKPointsRepo.addLastKnownPoints(lkPoints)
    }
}