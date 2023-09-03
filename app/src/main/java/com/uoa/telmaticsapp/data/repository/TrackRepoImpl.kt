package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.Track
import com.uoa.telmaticsapp.data.repository.datasource.ComputedTrackFrmPhoneSensors
import com.uoa.telmaticsapp.data.repository.datasource.TrackData
import com.uoa.telmaticsapp.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import java.util.*

class TrackRepoImpl(private val trackData: TrackData, private val computedTrackData: ComputedTrackFrmPhoneSensors): TrackRepository {
    override suspend fun saveTrack(track: Track) {
      trackData.saveTrack(track)
    }

    override fun getTracks(): Flow<List<Track>> {
       return trackData.getTracks()
    }

    override fun getATrack(trackId: String): Flow<Track> {
        return trackData.getATrack(trackId)
    }

    override suspend fun deleteTrack(track: Track) {
        trackData.deleteTrack(track)
    }

    override suspend fun updateTrack(track: Track) {
        trackData.updateTrack(track)
    }

    override fun computeTrack(trackId: String): Track {
        return computedTrackData.computeTrackDataFromHardware(trackId)
    }


}