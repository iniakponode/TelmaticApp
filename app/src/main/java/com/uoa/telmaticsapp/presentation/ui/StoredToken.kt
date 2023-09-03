package com.uoa.telmaticsapp.presentation.ui

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import kotlin.streams.asSequence

class StoredToken {

    companion object{

        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            java.util.Random().ints(8, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
        } else {
            // Descriptive alphabet using three CharRange objects, concatenated
            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

            // Build list from 20 random samples from the alphabet,
            // and convert it to a string using "" as element separator
            List(20) { alphabet.random() }.joinToString("")
        }

        fun getStoredID(sf:SharedPreferences,tokenFileID:String): String {
            val deviceToken = sf.getString(tokenFileID, null)
            val devT = deviceToken.toString()
            Log.i(tokenFileID, deviceToken.toString())
            return devT
        }
        fun validateToken(token:String):Boolean{
            return !(token==null)
        }


        }
    }