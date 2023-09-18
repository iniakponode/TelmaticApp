package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.model.SensorsModel
import kotlinx.coroutines.flow.Flow

interface PointRepository {
    suspend fun addPoint(trackPoint: TrackPoint)
    suspend fun deletePoint(trackPoint:TrackPoint)
    fun getPoints(): Flow<List<TrackPoint>>
    fun computePointsFromHardware(sensorData: SensorsModel,
                                  totalMeters:Double,
                                  deceleration:Double,
                                  trackId: String,
                                  speed:Double,
                                  midSpeed:Double,
                                  longitude:String,
                                  latitude:String): TrackPoint
}