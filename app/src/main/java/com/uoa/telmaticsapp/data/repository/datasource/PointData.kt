package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.TrackPoint
import kotlinx.coroutines.flow.Flow

interface PointData {
    suspend fun savePoint(trackPoint: TrackPoint)
    fun getPoints(): Flow<List<TrackPoint>>
    fun getAPoint(pointID: String): Flow<TrackPoint>
    suspend fun deletePoint(trackPoint: TrackPoint)
}