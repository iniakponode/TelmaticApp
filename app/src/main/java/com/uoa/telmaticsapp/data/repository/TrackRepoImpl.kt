package com.uoa.telmaticsapp.data.repository

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.repository.datasource.ComputedTrackFrmPhoneSensors
import com.uoa.telmaticsapp.data.repository.datasource.TrackData
import com.uoa.telmaticsapp.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class TrackRepoImpl(private val trackData: TrackData, private val computedTrackData: ComputedTrackFrmPhoneSensors): TrackRepository {
    override suspend fun saveTrack(externalFactorsModel: ExternalFactorsModel) {
      trackData.saveTrack(externalFactorsModel)
    }

    override fun getTracks(): Flow<List<ExternalFactorsModel>> {
       return trackData.getTracks()
    }

    override fun getATrack(trackId: String): Flow<ExternalFactorsModel> {
        return trackData.getATrack(trackId)
    }

    override suspend fun deleteTrack(externalFactorsModel: ExternalFactorsModel) {
        trackData.deleteTrack(externalFactorsModel)
    }

    override suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel) {
        trackData.updateTrack(externalFactorsModel)
    }

    override fun computeTrack(trackId: String): ExternalFactorsModel {
        return computedTrackData.computeTrackDataFromHardware(trackId)
    }


}