package com.uoa.telmaticsapp.data.repository.datasource

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ComputedLastKPFrmPhoneSensors {
    fun computeLastKnownPointsFromHardware(accuracy:String,
                                           lastKPId:String,
                                           lastTrackId:String,
                                           latitude:Double,
                                           longitude:Double,
                                           pointDate:String,
                                           startTrackId:String): LastKnownPoints
}