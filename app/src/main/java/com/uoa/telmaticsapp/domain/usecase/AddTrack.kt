package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.Track
import com.uoa.telmaticsapp.domain.repository.TrackRepository

class AddTrack(private val trackRepo: TrackRepository) {
    suspend fun execute(track: Track){
        trackRepo.saveTrack(track)
    }
}