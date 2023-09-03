package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.TrackRepository

class GetTracks(private val trackRepo: TrackRepository) {
    fun execute()=trackRepo.getTracks()
}