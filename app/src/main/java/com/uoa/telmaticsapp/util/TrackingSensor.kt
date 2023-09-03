package com.uoa.telmaticsapp.util

import java.sql.Timestamp
import java.util.*

abstract class TrackingSensor(
    protected val sensorType:Int
) {
    protected var onSensorValueChanged: ((List<Float>)->Unit)?=null
    protected var onAccuracyValueChanged: ((Int)->Unit)?=null
    protected var sensorTimestamp: ((String)->Unit)?=null
    abstract val doesSensorExist:Boolean
//    abstract var timeStamp:Date
    abstract fun startlisteningToSensor(rate:Int)
    abstract fun stopListeningToSensor()

    fun setOnSensorValuesChangedListener(listener: ((List<Float>)->Unit)){
        onSensorValueChanged=listener

    }
    fun setTimestampOnsensorChangedListener(listener: ((String)->Unit)){
        sensorTimestamp=listener
    }
    fun setOnAccuracyValueChangedListener(accurcy:Boolean, listener:((Int))->Unit){
        onAccuracyValueChanged=listener
    }
}