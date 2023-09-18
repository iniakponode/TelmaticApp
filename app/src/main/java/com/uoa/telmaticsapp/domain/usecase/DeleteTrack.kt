package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.domain.repository.TrackRepository

class DeleteTrack(private val trackRepo: TrackRepository) {
    suspend fun execute(externalFactorsModel:ExternalFactorsModel)=trackRepo.deleteTrack(externalFactorsModel)
}