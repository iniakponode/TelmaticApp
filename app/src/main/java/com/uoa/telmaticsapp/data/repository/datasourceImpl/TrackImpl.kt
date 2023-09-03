package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.db.DataAccessObjects.TrackDAO
import com.uoa.telmaticsapp.data.model.Track
import com.uoa.telmaticsapp.data.repository.datasource.TrackData
import kotlinx.coroutines.flow.Flow

class TrackImpl(private val trackDAO: TrackDAO): TrackData {
    override suspend fun saveTrack(track: Track) {
        trackDAO.insertTrack(track)
    }

    override fun getTracks(): Flow<List<Track>> {
        return trackDAO.getAllTracks()
    }

    override fun getATrack(trackId: String): Flow<Track> {
        return trackDAO.getATrack(trackId)
    }

    override suspend fun updateTrack(track: Track) {
        trackDAO.updateTrack(track)
    }

//    override suspend fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String) {
//        trackDAO.updateTrack(endDate, drunk_state, other_experiences, overloading, tripId)
//    }

    override suspend fun deleteTrack(track: Track) {
        trackDAO.deleteTrack(track)
    }
}