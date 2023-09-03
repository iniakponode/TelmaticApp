package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.PointRepository

class GetPoints(private val pointRepo: PointRepository) {
    fun execute()=pointRepo.getPoints()
}