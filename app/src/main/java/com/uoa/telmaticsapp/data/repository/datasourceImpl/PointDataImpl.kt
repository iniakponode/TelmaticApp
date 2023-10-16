package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.PointDAO
import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.repository.datasource.PointData
import kotlinx.coroutines.flow.Flow

class PointDataImpl(private val pointDataDAO: PointDAO) : PointData {
    override suspend fun savePoint(trackPoint: TrackPoint) {
       pointDataDAO.insertPoint(trackPoint)
    }

    override fun getPoints(): Flow<List<TrackPoint>> {
        return pointDataDAO.getAllPoints()
    }

    override fun getAPoint(pointID: String): Flow<TrackPoint> {
        return pointDataDAO.getAPoint(pointID)
    }

    override suspend fun deletePoint(trackPoint: TrackPoint) {
        pointDataDAO.deletePoint(trackPoint)
    }
}