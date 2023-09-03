package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.data.repository.datasource.ComputedLastKPFrmPhoneSensors
import kotlinx.coroutines.flow.Flow
import java.util.*

class ComputedLastKPFrmPhoneSensorsImpl :ComputedLastKPFrmPhoneSensors {
    override fun computeLastKnownPointsFromHardware(accuracy:String,
                                                    lastKPId:String,
                                                    lastTrackId:String,
                                                    latitude:Double,
                                                    longitude:Double,
                                                    pointDate:String,
                                                    startTrackId:String): LastKnownPoints {
        return LastKnownPoints(lastKPId,lastTrackId,latitude,longitude,pointDate,startTrackId)
    }
}