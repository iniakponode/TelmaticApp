package com.uoa.telmaticsapp.data.repository.datasourceImpl

import android.os.Build
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.repository.datasource.ComputedTrackFrmPhoneSensors
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ComputedTrackFrmPhoneSensorsImpl: ComputedTrackFrmPhoneSensors {
    override fun computeTrackDataFromHardware(trackId:String):ExternalFactorsModel {
//        val trackId=UUID.randomUUID()
        val startDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
        } else {
            // For Android versions below Oreo (API level 26), use SimpleDateFormat
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            sdf.format(Date())
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