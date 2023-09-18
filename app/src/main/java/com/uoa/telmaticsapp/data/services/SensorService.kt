package com.uoa.telmaticsapp.data.services

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.domain.usecase.StartSensors
import com.uoa.telmaticsapp.domain.usecase.StopSensors
import com.uoa.telmaticsapp.presentation.ui.AppStartHome
import com.uoa.telmaticsapp.presentation.ui.StoredToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SensorService() : Service() {

    private lateinit var locationManager: LocationManager
    private  lateinit var sensorsD: SensorsModel
    private var background = false
    private val notificationActivityRequestCode = 0
    private val notificationId = 1
    private val notificationStopRequestCode = 2

    @Inject
    lateinit var startSensors: StartSensors
    val stSensors: Flow<List<SensorsModel>>
        get()=startSensors.execute()
    @Inject
    lateinit var stopSensors: StopSensors
    val stopS
        get()=stopSensors.execute()

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        val notification = createNotification(getString(R.string.not_available),
            "Tracking not Started"
        )
        startForeground(notificationId, notification)

    }
    private fun createNotification(direction: String, message: String): Notification {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                application.packageName,
                "Notifications", NotificationManager.IMPORTANCE_DEFAULT
            )

            // Configure the notification channel.
            notificationChannel.enableLights(false)
            notificationChannel.setSound(null, null)
            notificationChannel.enableVibration(false)
            notificationChannel.vibrationPattern = longArrayOf(0L)
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(baseContext, application.packageName)
        // Open activity intent
        val contentIntent = PendingIntent.getActivity(
            this, notificationActivityRequestCode,
            Intent(this, AppStartHome::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
//         Stop notification intent
        val stopNotificationIntent = Intent(this, ActionListener::class.java)
        stopNotificationIntent.action = KEY_NOTIFICATION_STOP_ACTION
        stopNotificationIntent.putExtra(KEY_NOTIFICATION_ID, notificationId)
        val pendingStopNotificationIntent =
            PendingIntent.getBroadcast(this, notificationStopRequestCode, stopNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Tracking Started")
            .setWhen(System.currentTimeMillis())
            .setDefaults(0)
            .setVibrate(longArrayOf(0L))
            .setSound(null)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(contentIntent)
            .addAction(R.mipmap.ic_launcher_round, getString(R.string.stop_notifications), pendingStopNotificationIntent)


        return notificationBuilder.build()
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            GlobalScope.launch {
                startSensors()
            }

            background = it.getBooleanExtra(KEY_BACKGROUND, false)
        }

        return START_STICKY
    }


    fun startSensors() {
        val sensorsDataResult = startSensors.execute()
        Log.i("Size", sensorsDataResult.toString())
        val time=System.currentTimeMillis()
        val loc_date = Date(time)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dat_text: String = sdf.format(loc_date)

        val intent = Intent()
        var sensors_D = SensorsModel(
            StoredToken.tripId,
            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
            0,
            0.0f,
            0.0f,
            0.0f,
            ""
        )
        GlobalScope.launch {
            sensorsDataResult . collect {
//            listOfSensorData->
//            listOfSensorData.forEach { sensorData ->
                    sensorsDataResult ->
                sensorsDataResult.forEach { sensor ->

                    intent.putExtra(SENSORDID, sensor.sensorDataID.toString())
                    intent.putExtra(ACCELEROM, sensor.accelerom.toFloatArray())
//                    intent.putExtra(ACCELEROMLTDA, sensor.acceleromltdA.toFloatArray())
                    intent.putExtra(ACCELEROMLTDAUN, sensor.acceleromltdAUncalibrated.toFloatArray())
                    intent.putExtra(ACCELEROM_ACC, sensor.accelerAcc)
                    intent.putExtra(GRAVITY, sensor.gravity.toFloatArray())
                    intent.putExtra(GRAVITY_ACC, sensor.gravityAcc)
                    intent.putExtra(GYRO, sensor.gyroscope.toFloatArray())
//                    intent.putExtra(GYROLTDA, sensor.gyroscopeLtdA.toFloatArray())
                    intent.putExtra(GYROLTDAUN, sensor.gyroscopeLtdAUncalibrated.toFloatArray())
                    intent.putExtra(GYRO_ACC, sensor.gyroscopeAcc)
                    intent.putExtra(LIN_ACCELEROM, sensor.linearAcceleration.toFloatArray())
                    intent.putExtra(LIN_ACCELEROM_ACC, sensor.linAccelerAcc)
                    intent.putExtra(MAGNETO, sensor.magnetometer.toFloatArray())
                    intent.putExtra(MAGNETO_ACC, sensor.magnetometerAcc)
////                intent.putExtra(PROXIMITY,sensors_D.proximity.toFloatArray())
////                intent.putExtra(PROXIMITYACC,sensors_D.proximityAcc)
                    intent.putExtra(ROTVECT, sensor.RotVect.toFloatArray())
                    intent.putExtra(ROTVECTACC, sensor.rotVectAcc)
                    intent.putExtra(COLLECTED_DATE, sensor.date)
                    intent.putExtra(YAW, sensor.yaw)
                    intent.putExtra(PITCH, sensor.pitch)
                    intent.putExtra(ROLL, sensor.roll)
                    intent.putExtra(LOC_TIME, dat_text)
                    intent.action = KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION
//
////                intent.putExtra("sensorData",sensors_D as FloatArray)
//                Log.i("Sens",sensor.RotVect[0].toString())
                    LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
                }
            }
        }
//        return sensors_D
    }


//        intent.putExtra(LATITUDE,latitude)
//        intent.putExtra(LONGITUDE,longitude)
//        intent.putExtra(SPEED,speed)
//        intent.putExtra(LOC_ACCURACY,accuracy)
//        intent.putExtra(DISTANCE,distance)




//    }

//    fun stopSensors(context: Context)=GlobalScope.launch(Dispatchers.Default){
//        stopSensors.execute()
//        val sensorServiceIntent=Intent(context, SensorsService::class.java)
//        sensorServiceIntent.action=KEY_STOP_SERVICE
//        sensorServiceIntent.putExtra("SENSORS_SERVICE","Stoping Sensors Service......")
//        LocalBroadcastManager.getInstance(context).sendBroadcast(sensorServiceIntent)
//    }
    override fun onDestroy() {
        super.onDestroy()
//       stopSensors= stopSensors.execute()
    val sensorServiceIntent=Intent()
    sensorServiceIntent.action= SensorService.KEY_STOP_SERVICE
    sensorServiceIntent.putExtra("STOP_SENSORS","Stopping Sensors Service......")
    sensorServiceIntent.putExtra(SensorService.KEY_NOTIFICATION_ID,-1)
    stopSelf()
    }
    companion object{
        val KEY_BACKGROUND = "background"
        val KEY_NOTIFICATION_ID = "notificationId"
        val KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION="com.uoa.android.telmatics.data.services.SENSORS_LOCATION_CHANGED"
        val KEY_GET_NEW_SENSOR_DATA="com.uoa.android.telmatics.data.services.SENSOR_DATA_CHANGED"
        val KEY_NOTIFICATION_STOP_ACTION = "com.uoa.android.telmatics.data.services.NOTIFICATION_STOP"
        val KEY_STOP_SERVICE="com.uoa.android.telmatics.data.services.STOP_SERVICE"
        val KEY_START_SERVICE="com.uoa.android.telmatics.data.services.START_SERVICE"
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
        val LOC_TIME="LOCATION_TIME"
        val COLLECTED_DATE="COLLECTED_DATE"
        val TrackPOINT="TrackPoint"
        val ExternalFactorsModel="ExternalFactorsModel"
        val LASTPOINTS="LastKnownPoints"
        val LONGITUDE="longitude"
        val LATITUDE="latitude"
        val SENSORSDATA="sensorsData"
        val DISTANCE="totalMeters"
        val SPEED="speed"
        val LOC_ACCURACY="accuracy"
        val KEY_LOCATION_VALUE_CHANGED="LOCATION_CHANGED"

//        fun stopSensorsService(context: Context){
//            val sensorServiceIntent=Intent(context, SensorsService::class.java)
//            sensorServiceIntent.action=KEY_STOP_SERVICE
//            sensorServiceIntent.putExtra("SENSORS_SERVICE","Stoping Sensors Service......")
//            LocalBroadcastManager.getInstance(context).sendBroadcast(sensorServiceIntent)
//
//            GlobalScope.launch(Dispatchers.Default){
//                stopSensors.execute()
//        }


    }
    class ActionListener : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            if (intent != null && intent.action != null) {
                if (intent.action.equals(KEY_NOTIFICATION_STOP_ACTION)||intent.action.equals(KEY_STOP_SERVICE)) {
                    context?.let {
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val sensorsIntent = Intent(context, SensorService::class.java)
                        context.stopService(sensorsIntent)
                        val notificationId = intent.getIntExtra(KEY_NOTIFICATION_ID, -1)
                        if (notificationId != -1) {
                            notificationManager.cancel(notificationId)
                        }
                    }
                }
            }
        }
    }
}