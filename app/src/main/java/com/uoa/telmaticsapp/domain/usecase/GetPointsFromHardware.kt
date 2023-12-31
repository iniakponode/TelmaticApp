package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.domain.repository.PointRepository

class GetPointsFromHardware(private val pointRepo: PointRepository) {
    fun execute(sensorData: SensorsModel,
                totalMeters:Double,
                deceleration:Double,
                trackId: String,
                speed:Double,
                midSpeed:Double,
                longitude:String,
                latitude:String)=
        pointRepo.computePointsFromHardware(
                sensorData,
                totalMeters,
                deceleration,
                trackId,
                speed,
                midSpeed,
                longitude,
                latitude
                )
}