package com.uoa.telmaticsapp.util

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import kotlin.streams.asSequence

class StoredToken {

    companion object{

        fun getStoredDeviceToken(sf:SharedPreferences,tokenFileID:String): String {
            val deviceToken = sf.getString(tokenFileID, null)
            val devT = deviceToken.toString()
            Log.i("DeviceModel-TokenSaved", deviceToken.toString())
            return devT
        }
        fun validateToken(token:String):Boolean{
            return !(token==null)
        }


        }
    }