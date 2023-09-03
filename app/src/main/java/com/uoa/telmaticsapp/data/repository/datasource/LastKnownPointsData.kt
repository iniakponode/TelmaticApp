package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LastKnownPointsData {
    suspend fun addLastKnownPoints(points: LastKnownPoints)
    fun getLastKnownPoints(): List<LastKnownPoints>
    fun getALastKnownPoint(lastKPointId: UUID): Flow<LastKnownPoints>
    suspend fun deleteLastKnownPoints(lastKnownPoints: LastKnownPoints)
}