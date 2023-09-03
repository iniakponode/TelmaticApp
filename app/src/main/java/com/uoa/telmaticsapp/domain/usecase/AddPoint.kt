package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.domain.repository.PointRepository

class AddPoint(private  val pointRepo: PointRepository) {
    suspend fun execute(trackPoint: TrackPoint){
        pointRepo.addPoint(trackPoint)
    }
}