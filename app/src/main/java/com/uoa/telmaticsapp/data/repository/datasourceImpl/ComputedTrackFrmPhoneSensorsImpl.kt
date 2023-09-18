package com.uoa.telmaticsapp.data.repository.datasourceImpl

import android.os.Build
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.repository.datasource.ComputedTrackFrmPhoneSensors
import java.time.Instant
import java.time.format.DateTimeFormatter

class ComputedTrackFrmPhoneSensorsImpl: ComputedTrackFrmPhoneSensors {
    override fun computeTrackDataFromHardware(trackId:String):ExternalFactorsModel {
//        val trackId=UUID.randomUUID()
        val startDate=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
        } else {

        }

        val externalFactorsModel=ExternalFactorsModel(
            trackId.toString(),
            startDate.toString(),
            "",
            "",
            "",
            ""
        )
    return externalFactorsModel

    }
}