package com.uoa.telmaticsapp.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import javax.inject.Inject

abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    sensorType: Int
): TrackingSensor(sensorType), SensorEventListener {

//    implement sensorExists function here
//    override val sensorExists: Boolean
//        get() = context.packageManager.hasSystemFeature(sensorFeature)
//
////declare Android sensor manager and sensor values holder variables
//    private lateinit var sensorManager:SensorManager
//    private var sensor: Sensor?=null
//
////    Call start listening function to listen to sensor if it exists
//    override fun startlisteningToSensor(rate:Int) {
//        if (!sensorExists)
//            return
//        //check if sensor is called for the first time, which means it is not yet initialized
//        // and there are no values in the sensor variable yet. In that case initialize the sensor
//        if (!::sensorManager.isInitialized && sensor==null){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                this.sensorManager = context.getSystemService(sensorManager::class.java) as SensorManager
//            } // initialize sensorManager
//            sensor = sensorManager.getDefaultSensor(sensorType)
//        }
//
////        start listening to the sensor
//        sensor?.let {
//            sensorManager.registerListener(this,it,rate)
//        }
//    }
//
////    stop listening to the sensor
//    override fun stopListeningToSensor() {
//        if(!sensorExists || !::sensorManager.isInitialized)
//            return
//        sensor?.let {
//            sensorManager.unregisterListener(this)
//        }
//
//    }
//
//    override fun onSensorChanged(event: SensorEvent?) {
//        if(!sensorExists)
//            return
//        if(event?.sensor?.type==sensorType){
//            onSensorValueChanged?.invoke(event.values.toList())
//        }
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int){
//
//        if (!sensorExists||sensor!=sensorManager.getDefaultSensor(sensorType))
//            return
//        onAccuracyValueChanged?.invoke(accuracy)
//    }



    //    implement sensorExists function here
    override val doesSensorExist: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun startlisteningToSensor(rate:Int) {
        if(!doesSensorExist) {
            return
        }
        if(!::sensorManager.isInitialized && sensor == null) {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
        }
        sensor?.let {
            sensorManager.registerListener(this, it, rate)
        }
    }

    override fun stopListeningToSensor() {
        if(!doesSensorExist || !::sensorManager.isInitialized) {
            return
        }
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(!doesSensorExist) {
            return
        }
        if(event?.sensor?.type == sensorType) {
            onSensorValueChanged?.invoke(event.values.toList())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int){

        if (!doesSensorExist||sensor!=sensorManager.getDefaultSensor(sensorType))
            return
        onAccuracyValueChanged?.invoke(accuracy)
    }

}