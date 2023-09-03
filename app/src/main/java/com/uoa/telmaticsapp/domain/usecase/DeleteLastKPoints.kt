package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo

class DeleteLastKPoints(private val lKPointsRepo: LastKnownPointsRepo) {
    suspend fun execute(lkpoints:LastKnownPoints){
        lKPointsRepo.deleteLastKnownPoints(lkpoints)
    }
}