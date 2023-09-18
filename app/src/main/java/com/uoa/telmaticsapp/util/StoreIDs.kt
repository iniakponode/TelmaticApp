package com.uoa.telmaticsapp.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.util.*

class StoreIDs {
    companion object{
        fun storeTrackIDLocally(context: Context, trackID: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_trackId", trackID.toString())
            }.apply()
            Toast.makeText(context,"Track_ID Saved Successfully", Toast.LENGTH_LONG)

        }

        fun storeStartDateLocally(context: Context, startDate: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_start_date", startDate)
            }.apply()
            Toast.makeText(context,"ExternalFactorsModel Start Date Saved Successfully", Toast.LENGTH_LONG)

        }
        fun storeStarLastSIDLocally(context: Context, sID: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_last_SID", sID)
            }.apply()
            Toast.makeText(context,"Last Sensor ID Saved Successfully", Toast.LENGTH_LONG)

        }
        fun storeStartTrackIdLocally(context: Context, startTrackId:String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("start_trackId", startTrackId)
            }.apply()
            Toast.makeText(context,"Start TrackId Saved Successfully", Toast.LENGTH_LONG)

        }

        fun storeEndDateLocally(context: Context, endDate: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_end_date", endDate)
            }.apply()
            Toast.makeText(context,"ExternalFactorsModel end date Saved Successfully", Toast.LENGTH_LONG)

        }

        fun storeSensorsIDLocally(context: Context,sensorsID: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_sensorsId", sensorsID)
            }.apply()
            Toast.makeText(context,"SensorsID Saved Successfully", Toast.LENGTH_LONG)

        }
        fun storeDeviceTokenLocally(context: Context,deviceToken: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_device_token", deviceToken)
            }.apply()
            Toast.makeText(context,"DeviceModel Token Saved Successfully", Toast.LENGTH_LONG)

        }

        fun storeTelDeviceTokenLocally(context: Context,deviceToken: String, edtor: SharedPreferences.Editor){

            edtor.apply {
                putString("saved_tel_device_token", deviceToken)
            }.apply()
            Toast.makeText(context,"DeviceModel Token Saved Successfully", Toast.LENGTH_LONG)

        }

    }
}