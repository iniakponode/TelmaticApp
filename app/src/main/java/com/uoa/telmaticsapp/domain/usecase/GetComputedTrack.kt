package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.TrackRepository
import java.util.*

class GetComputedTrack(private val trackRepo:TrackRepository) {
    fun execute(trackId: String)=trackRepo.computeTrack(trackId)
}