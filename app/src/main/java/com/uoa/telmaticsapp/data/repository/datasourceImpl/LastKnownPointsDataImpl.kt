package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.LastKnownPointsDAO
import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.data.repository.datasource.LastKnownPointsData
import kotlinx.coroutines.flow.Flow
import java.util.*

class LastKnownPointsDataImpl(private val lastKnwnDAO: LastKnownPointsDAO): LastKnownPointsData {
    override suspend fun addLastKnownPoints(points: LastKnownPoints) {
        lastKnwnDAO.addLastKPoint(points)
    }

    override fun getLastKnownPoints(): List<LastKnownPoints> {
        return lastKnwnDAO.getAllLastKPoints()
    }

    override fun getALastKnownPoint(lastKPointId: UUID): Flow<LastKnownPoints> {
        return lastKnwnDAO.getALastKPoint(lastKPointId)
    }

    override suspend fun deleteLastKnownPoints(lastKnownPoints: LastKnownPoints) {
        lastKnwnDAO.deleteLastKPoint(lastKnownPoints)
    }
}