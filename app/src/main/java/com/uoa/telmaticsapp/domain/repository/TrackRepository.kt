package com.uoa.telmaticsapp.domain.repository
import com.uoa.telmaticsapp.data.model.Track
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TrackRepository {

    suspend fun saveTrack(track: Track)
    fun getTracks(): Flow<List<Track>>
    fun getATrack(trackId: String): Flow<Track>
    suspend fun deleteTrack(track: Track)
    suspend fun updateTrack(track: Track)
    fun computeTrack(trackId: String):Track
}