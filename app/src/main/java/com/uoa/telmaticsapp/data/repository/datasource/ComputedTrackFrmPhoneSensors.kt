package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.Track
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ComputedTrackFrmPhoneSensors {
    fun computeTrackDataFromHardware(trackId:String): Track
}