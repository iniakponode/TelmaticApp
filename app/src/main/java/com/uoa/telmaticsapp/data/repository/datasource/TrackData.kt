package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import kotlinx.coroutines.flow.Flow

interface TrackData {

    suspend fun saveTrack(externalFactorsModel: ExternalFactorsModel)
    fun getTracks(): Flow<List<ExternalFactorsModel>>
    fun getATrack(trackId: String): Flow<ExternalFactorsModel>
//    suspend fun updateTrack(endDate: String?, drunk_state: String?, other_experiences: String?, overloading:String?, tripId:String)
    suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel)
    suspend fun deleteTrack(externalFactorsModel: ExternalFactorsModel)
}