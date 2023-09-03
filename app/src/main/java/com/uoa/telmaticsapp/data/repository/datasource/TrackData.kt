package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackData {

    suspend fun saveTrack(track: Track)
    fun getTracks(): Flow<List<Track>>
    fun getATrack(trackId: String): Flow<Track>
//    suspend fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String)
    suspend fun updateTrack(track: Track)
    suspend fun deleteTrack(track: Track)
}