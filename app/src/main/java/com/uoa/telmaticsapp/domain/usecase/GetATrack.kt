package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.TrackRepository

class GetATrack(private val trackRepo: TrackRepository) {
    fun execute(trackId: String)=trackRepo.getATrack(trackId)
}