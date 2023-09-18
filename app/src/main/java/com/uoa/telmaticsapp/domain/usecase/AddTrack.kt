package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.domain.repository.TrackRepository

class AddTrack(private val trackRepo: TrackRepository) {
    suspend fun execute(externalFactorsModel: ExternalFactorsModel){
        trackRepo.saveTrack(externalFactorsModel)
    }
}