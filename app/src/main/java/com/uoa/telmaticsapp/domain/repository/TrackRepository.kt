package com.uoa.telmaticsapp.domain.repository
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    suspend fun saveTrack(externalFactorsModel: ExternalFactorsModel)
    fun getTracks(): Flow<List<ExternalFactorsModel>>
    fun getATrack(trackId: String): Flow<ExternalFactorsModel>
    suspend fun deleteTrack(externalFactorsModel: ExternalFactorsModel)
    suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel)
    fun computeTrack(trackId: String):ExternalFactorsModel
}