package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel

interface ComputedTrackFrmPhoneSensors {
    fun computeTrackDataFromHardware(trackId:String): ExternalFactorsModel
}