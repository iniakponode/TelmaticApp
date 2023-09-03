package com.uoa.telmaticsapp.domain.repository

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LastKnownPointsRepo {
    suspend fun addLastKnownPoints(points:LastKnownPoints)
    fun getLastKnownPoints(): List<LastKnownPoints>
    fun getALastKnwonPoint(lastKPointId: UUID):Flow<LastKnownPoints>
    suspend fun deleteLastKnownPoints(lastKnownPoints: LastKnownPoints)
    fun computeLastKnownPointsFromHardware(
//        accuracy: String,
        lastKPId:String,
        lastTrackId: String,
        latitude: Double,
        longitude: Double,
        pointDate: String,
        startTrackId: String): LastKnownPoints
}