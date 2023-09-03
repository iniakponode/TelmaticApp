package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo

class GetLasKPoints(private val lkPointsRepo:LastKnownPointsRepo) {
    fun execute()=lkPointsRepo.getLastKnownPoints()
}