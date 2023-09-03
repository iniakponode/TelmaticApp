package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.data.repository.datasource.ComputedPointFrmPhoneSensors
import com.uoa.telmaticsapp.data.repository.datasource.PointData
import com.uoa.telmaticsapp.domain.repository.PointRepository
import kotlinx.coroutines.flow.Flow

class PointRepoImpl(private val pointData: PointData, private val computedPointFrmPhoneSensors: ComputedPointFrmPhoneSensors): PointRepository {
    override suspend fun addPoint(trackPoint: TrackPoint) {
        pointData.savePoint(trackPoint)
    }

    override suspend fun deletePoint(trackPoint: TrackPoint) {
        pointData.deletePoint(trackPoint)
    }

    override fun getPoints(): Flow<List<TrackPoint>> {
        return pointData.getPoints()
    }

    override fun computePointsFromHardware(
        sensorData: SensorsData,
        totalMeters: Double,
        deceleration: Double,
        trackId: String,
        speed: Double,
        midSpeed: Double,
        longitude: String,
        latitude: String
    ): TrackPoint {
        return computedPointFrmPhoneSensors.computePointsFromHardware(sensorData,
            totalMeters,
            deceleration,
            trackId,
            speed,
            midSpeed,
            longitude,
            latitude
        )
    }
}