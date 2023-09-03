package com.uoa.telmaticsapp.data.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.uoa.telmaticsapp.domain.usecase.StartSensors
import com.uoa.telmaticsapp.domain.usecase.StopSensors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val GENERATE_SENSOR_DATA = "com.uoa.telmaticsapp.data.services.sensorsService.startSensors"
private const val STOP_SENSORS = "com.uoa.telmaticsapp.presentation.services.sensorsService.stopSensor"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.uoa.telmaticsapp.presentation.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.uoa.telmaticsapp.presentation.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class SensorsService() : IntentService("SensorsService_thread"),LocationListener {
@Inject
lateinit var startSensors: StartSensors
val stSensors
 get() = startSensors.execute()

@Inject
lateinit var stopSensors:StopSensors
val stopSens:Any
    get()=GlobalScope.launch { stopSensors.execute()

    }

    companion object {
        val KEY_BACKGROUND = "background"
        val KEY_NOTIFICATION_ID = "notificationId"
        val KEY_LOCATION_CHANGED_ACTION="com.uoa.android.telmatics.data.services.LOCATION_CHANGED"
        val KEY_GET_NEW_SENSOR_DATA="com.uoa.android.telmatics.data.services.SENSOR_DATA_CHANGED"
        val KEY_NOTIFICATION_STOP_ACTION = "com.uoa.android.telmatics.data.services.NOTIFICATION_STOP"
        val SENSORDID="SENSORID"
        val ACCELEROM="ACCELEROMETER"
        val ACCELEROMLTDA="ACCELEROMETERLTDA"
        val ACCELEROMLTDAUN="ACCELEROMETER_LTD_A_UN"
        val ACCELEROM_ACC="ACCELEROM_ACC"
        val GYRO="GYRO"
        val GYROLTDA="GYRO_LTDA"
        val GYROLTDAUN="GYRO_LTDA_UN"
        val GYRO_ACC="GYRO_ACC"
        val GRAVITY="GRAVITY"
        val GRAVITY_ACC="GRAVITY_ACC"
        val LIN_ACCELEROM="LIN_ACCELEROM"
        val LIN_ACCELEROM_ACC="LIN_ACCELEROM_ACC"
        val MAGNETO="MAGNETO"
        val MAGNETO_ACC="MAGNETO"
        val PROXIMITY="PROXIMITY"
        val PROXIMITYACC="PROXIMITY_ACC"
        val ROTVECT="ROTATIONAL_VECTOR"
        val ROTVECTACC="ROTATIONAL_VECTOR_ACC"
        val YAW="YAW"
        val PITCH="PITCH"
        val ROLL="ROLL"
        val COLLECTED_DATE="COLLECTED_DATE"
        val TrackPOINT="TrackPoint"
        val TRACK="Track"
        val LASTPOINTS="LastKnownPoints"
        val LONGITUDE="longitude"
        val LATITUDE="latitude"
        val SENSORSDATA="sensorsData"
        val DISTANCE="totalMeters"
        val SPEED="speed"
        val LOC_ACCURACY="accuracy"

    }

    private var currentBestLocation: Location? = null
    val ONESECOND = 1000
    val loc= getSystemService(LOCATION_SERVICE) as LocationManager

    override fun onHandleIntent(intent: Intent?) {
        GlobalScope.launch ((Dispatchers.IO)){
            startSensors()
        }
        if (intent != null) {
            intent.getStringExtra("Started")?.let { Log.i("KLM", it) }
        }

    }

    suspend fun startSensors() {
           val sensorsDataFlow = startSensors.execute()
//           var sensorD= SensorsData(
//            "", listOf(0.0f,0.0f,0.0f),listOf(0.0f,0.0f,0.0f),listOf(0.0f,0.0f,0.0f),0,
//            listOf(0.0f,0.0f,0.0f),0,listOf(0.0f,0.0f,0.0f),listOf(0.0f,0.0f,0.0f),listOf(0.0f,0.0f,0.0f),0,
//            listOf(0.0f,0.0f,0.0f),0,listOf(0.0f,0.0f,0.0f),0,listOf(0.0f,0.0f,0.0f),0,
//            0.0f,0.0f,0.0f,"")
//           sensorsDataFlow.collect { listOfSensorData ->
        Log.i("Size",sensorsDataFlow.toString())
//        sensorsDataFlow.forEach { sensorData ->
//                   sensorD = sensorData
////               }
//
//           }
//        return sensorD
        }
    fun stopSensors(context: Context)=GlobalScope.launch(Dispatchers.Default) {
            stopSensors.execute()
        }

    override fun onLocationChanged(location: Location) {
        makeUseOfNewLocation(location);

        if(currentBestLocation == null){
            currentBestLocation = location;
        }

        GlobalScope.launch {
            val latitude = currentBestLocation!!.latitude.toString()
            val longitude = currentBestLocation!!.longitude.toString()
            val distance = currentBestLocation!!.distanceTo(currentBestLocation!!)
            val speed =currentBestLocation!!.speed
            val accuracy =currentBestLocation!!.accuracy

            val intent=Intent()
               intent.putExtra(LATITUDE,latitude)
               intent.putExtra(LONGITUDE,longitude)
               intent.putExtra(SPEED,speed)
               intent.putExtra(LOC_ACCURACY,accuracy)
               intent.putExtra(DISTANCE,distance)
               intent.action= KEY_LOCATION_CHANGED_ACTION

        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        }
    }

    /**
     * This method modify the last know good location according to the arguments.
     *
     * @param location The possible new location.
     */
    fun makeUseOfNewLocation(location: Location) {

        if (isBetterLocation(location, currentBestLocation)) {
            currentBestLocation = location
        }
    }

    /** Determines whether one location reading is better than the current location fix
     * @param location  The new location that you want to evaluate
     * @param currentBestLocation  The current location fix, to which you want to compare the new one.
     */
    protected fun isBetterLocation(location: Location, currentBestLocation: Location?): Boolean {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true
        }

        // Check whether the new location fix is newer or older
        val timeDelta = location.time - currentBestLocation.time
        val isSignificantlyNewer: Boolean = timeDelta > ONESECOND
        val isSignificantlyOlder: Boolean = timeDelta < -ONESECOND
        val isNewer = timeDelta > 0

        // If it's been more than two minutes since the current location, use the new location,
        // because the user has likely moved.
        if (isSignificantlyNewer) {
            return true
            // If the new location is more than One Second older, it must be worse.
        } else if (isSignificantlyOlder) {
            return false
        }

        // Check whether the new location fix is more or less accurate
        val accuracyDelta = (location.accuracy - currentBestLocation.accuracy).toInt()
        val isLessAccurate = accuracyDelta > 0
        val isMoreAccurate = accuracyDelta < 0
        val isSignificantlyLessAccurate = accuracyDelta > 200

        // Check if the old and new location are from the same provider
        val isFromSameProvider = isSameProvider(
            location.provider,
            currentBestLocation.provider
        )

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true
        } else if (isNewer && !isLessAccurate) {
            return true
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true
        }
        return false
    }

    // Checks whether two providers are the same
    private fun isSameProvider(provider1: String?, provider2: String?): Boolean {
        return if (provider1 == null) {
            provider2 == null
        } else provider1 == provider2
    }

}