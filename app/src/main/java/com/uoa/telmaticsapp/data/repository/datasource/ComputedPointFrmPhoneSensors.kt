package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.model.SensorsData

interface ComputedPointFrmPhoneSensors {
    fun computePointsFromHardware(sensorData:SensorsData,
                                  totalMeters:Double,
                                  deceleration:Double,
                                  trackId:String,
                                  speed:Double,
                                  midSpeed:Double,
                                  longitude:String,
                                  latitude:String): TrackPoint
}