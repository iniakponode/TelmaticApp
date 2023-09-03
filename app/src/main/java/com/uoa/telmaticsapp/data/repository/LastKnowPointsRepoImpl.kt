package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.data.repository.datasource.LastKnownPointsData
import com.uoa.telmaticsapp.domain.repository.LastKnownPointsRepo
import kotlinx.coroutines.flow.Flow
import java.util.*

class LastKnowPointsRepoImpl(private val lastKnPointsData: LastKnownPointsData): LastKnownPointsRepo {
    override suspend fun addLastKnownPoints(lKpoints: LastKnownPoints) {
       lastKnPointsData.addLastKnownPoints(lKpoints)
    }

    override fun getLastKnownPoints(): List<LastKnownPoints> {
        return lastKnPointsData.getLastKnownPoints()
    }

    override fun getALastKnwonPoint(lastKPointId: UUID): Flow<LastKnownPoints> {
        return lastKnPointsData.getALastKnownPoint(lastKPointId)
    }

    override suspend fun deleteLastKnownPoints(lastKnownPoints: LastKnownPoints) {
        lastKnPointsData.deleteLastKnownPoints(lastKnownPoints)
    }

    override fun computeLastKnownPointsFromHardware(
        lastKPId:String,
        lastTrackId: String,
        latitude: Double,
        longitude: Double,
        pointDate: String,
        startTrackId: String
    ): LastKnownPoints {
        return LastKnownPoints(lastKPId,lastTrackId,latitude,longitude,pointDate,startTrackId)
    }


}