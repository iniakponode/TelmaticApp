package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.TrackDAO
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.repository.datasource.TrackData
import kotlinx.coroutines.flow.Flow

class TrackImpl(private val trackDAO: TrackDAO): TrackData {
    override suspend fun saveTrack(externalFactorsModel: ExternalFactorsModel) {
        trackDAO.insertTrack(externalFactorsModel)
    }

    override fun getTracks(): Flow<List<ExternalFactorsModel>> {
        return trackDAO.getAllTracks()
    }

    override fun getATrack(trackId: String): Flow<ExternalFactorsModel> {
        return trackDAO.getATrack(trackId)
    }

    override suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel) {
        trackDAO.updateTrack(externalFactorsModel)
    }

//    override suspend fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String) {
//        trackDAO.updateTrack(endDate, drunk_state, other_experiences, overloading, tripId)
//    }

    override suspend fun deleteTrack(externalFactorsModel: ExternalFactorsModel) {
        trackDAO.deleteTrack(externalFactorsModel)
    }
}