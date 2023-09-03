//package com.uoa.telmaticsapp.data.services
//
//import android.app.Application
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.content.pm.ServiceInfo.*
//import android.os.Build
//import android.util.Log
//import android.util.TimeUtils
//import androidx.annotation.RequiresApi
//import androidx.core.app.NotificationCompat
//import androidx.work.*
//import com.uoa.telmaticsapp.R
//import com.uoa.telmaticsapp.data.model.SensorsData
//import com.uoa.telmaticsapp.domain.usecase.StartSensors
//import com.uoa.telmaticsapp.domain.usecase.StopSensors
//import dagger.assisted.Assisted
//import java.util.*
//import androidx.hilt.work.HiltWorker
//import dagger.assisted.AssistedInject
//import java.text.SimpleDateFormat
//import javax.inject.Inject
//import kotlin.streams.asSequence
//
//@HiltWorker
//class SensorsWorker  @AssistedInject constructor(
//    @Assisted context: Context,
//    @Assisted parameters: WorkerParameters,
//    val startSensors: StartSensors,
////    @Assisted app: Application,
////    @Assisted workManager: WorkManager,
//    val stopSensors: StopSensors) :
//    CoroutineWorker(context, parameters) {
////    @Inject
////    lateinit var startSensors: StartSensors
////    val stSensors: Flow<List<SensorsData>> = startSensors.execute(UUID.randomUUID().toString())
////
////    @Inject
////    lateinit var stopSensors: StopSensors
////    val stopSens:Any
////        get()= GlobalScope.launch { stopSensors.execute()
////
////        }
//
//
//    private var notificationManager =
//        context.getSystemService(Context.NOTIFICATION_SERVICE) as
//                NotificationManager
//    private val notificationChannel = "com.uoa.notifications.channel1"
//    override suspend fun doWork(): Result {
//        return try {
//            val appContext = applicationContext
//            // Mark the Worker as important
//            val progress = "Tracking Started"
//            setForeground(createForegroundInfo(progress))
//            val sensorData = startSensors()
//
//            val outputData1 = workDataOf("SENSOR_DATA" to sensorData)
//
//            val outputData = createOutputData("successfulData", sensorData)
//            Result.success(outputData)
//        } catch (e: Exception) {
//            val outputData = createOutputData("Error occurred from output", startSensors())
//            Result.failure(outputData)
//        }
//    }
//
//    private fun createOutputData(s: String, sensorData: SensorsData): Data {
////        val loc_date = Date(time)
////        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////        val dat_text: String = sdf.format(loc_date)
//        return Data.Builder()
////            .putString(SensorService.LATITUDE,latitude)
////        intent.putExtra(SensorService.LONGITUDE,longitude)
////        intent.putExtra(SensorService.SPEED,speed)
////        intent.putExtra(SensorService.LOC_ACCURACY,accuracy)
////        intent.putExtra(SensorService.DISTANCE,distance)
//        .putString(SensorService.SENSORDID,sensorData.sensorDataID.toString())
//        .putFloatArray(SensorService.ACCELEROM,sensorData.accelerom.toFloatArray())
//        .putFloatArray(SensorService.ACCELEROMLTDA,sensorData.acceleromltdA.toFloatArray())
//        .putFloatArray(SensorService.ACCELEROMLTDAUN,sensorData.acceleromltdAUncalibrated.toFloatArray())
//        .putInt(SensorService.ACCELEROM_ACC,sensorData.accelerAcc)
//        .putFloatArray(SensorService.GRAVITY,sensorData.gravity.toFloatArray())
//        .putInt(SensorService.GRAVITY_ACC,sensorData.gravityAcc)
//        .putFloatArray(SensorService.GYRO,sensorData.gyroscope.toFloatArray())
//        .putFloatArray(SensorService.GYROLTDA,sensorData.gyroscopeLtdA.toFloatArray())
//        .putFloatArray(SensorService.GYROLTDAUN,sensorData.gyroscopeLtdAUncalibrated.toFloatArray())
//        .putInt(SensorService.GYRO_ACC,sensorData.gyroscopeAcc)
//        .putFloatArray(SensorService.LIN_ACCELEROM,sensorData.linearAcceleration.toFloatArray())
//        .putInt(SensorService.LIN_ACCELEROM_ACC,sensorData.linAccelerAcc)
//        .putFloatArray(SensorService.MAGNETO,sensorData.magnetometer.toFloatArray())
//        .putInt(SensorService.MAGNETO_ACC,sensorData.magnetometerAcc)
//        .putFloatArray(SensorService.PROXIMITY,sensorData.proximity.toFloatArray())
//        .putInt(SensorService.PROXIMITYACC,sensorData.proximityAcc)
//        .putFloatArray(SensorService.ROTVECT,sensorData.RotVect.toFloatArray())
//        .putInt(SensorService.ROTVECTACC,sensorData.rotVectAcc)
//        .putString(SensorService.COLLECTED_DATE,sensorData.date)
//        .putFloat(SensorService.YAW,sensorData.yaw)
//        .putFloat(SensorService.PITCH,sensorData.pitch)
//        .putFloat(SensorService.ROLL,sensorData.roll)
////        .putString(SensorService.LOC_TIME,dat_text)
//        .build()
//    }
//
//
//    suspend fun startSensors(): SensorsData {
//        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            java.util.Random().ints(8, 0, source.length)
//                .asSequence()
//                .map(source::get)
//                .joinToString("")
//        } else {
//            // Descriptive alphabet using three CharRange objects, concatenated
//            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//
//            // Build list from 20 random samples from the alphabet,
//            // and convert it to a string using "" as element separator
//            List(20) { alphabet.random() }.joinToString("")
//        }
//        val sensorsDataFlow = startSensors.execute()
//        var sensorD = SensorsData(
//            tripId,
//            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            listOf(0.0f, 0.0f, 0.0f),
//            0,
//            0.0f,
//            0.0f,
//            0.0f,
//            ""
//        )
//        sensorsDataFlow.collect { listOfSensorData ->
//            listOfSensorData.forEach { sensorData ->
//                sensorD = sensorData
//                setForeground(createForegroundInfo("Sensor: " + sensorD.sensorDataID.toString()))
//            }
//
//        }
//
//        return sensorD
//        // Calls setForeground() periodically when it needs to update
//        // the ongoing Notification
//    }
//
//    // Creates an instance of ForegroundInfo which can be used to update the
//    // ongoing notification.
//    private fun createForegroundInfo(progress: String): ForegroundInfo {
//        val id = applicationContext.getString(R.string.notification_channel_id)
//        val title = applicationContext.getString(R.string.notification_title)
//        val cancel = applicationContext.getString(R.string.end_trip)
//        // This PendingIntent can be used to cancel the worker
//        val intent = WorkManager.getInstance(applicationContext)
//            .createCancelPendingIntent(getId())
//
//        // Create a Notification channel if necessary
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createChannel(
//                notificationChannel,
//                "SensorsChannel",
//                "Notificiation Channel for Sensors"
//            )
//        }
//
//        val notification = NotificationCompat.Builder(applicationContext, notificationChannel)
//            .setContentTitle(title)
//            .setTicker(title)
//            .setContentText(progress)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setOngoing(true)
//            // Add the cancel action to the notification which can
//            // be used to cancel the worker
//            .addAction(R.drawable.ic_launcher_foreground, cancel, intent)
//            .build()
//
//        val notificationId = 2
//        return ForegroundInfo(
//            notificationId, notification,
//            FOREGROUND_SERVICE_TYPE_LOCATION or
//                    FOREGROUND_SERVICE_TYPE_PHONE_CALL or FOREGROUND_SERVICE_TYPE_DATA_SYNC
//        )
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createChannel(id: String, name: String, channelDesc: String) {
//        // Create a Notification channel
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel(id, name, importance).apply {
//            description = channelDesc
//        }
//        notificationManager?.createNotificationChannel(channel)
//    }
//
//    companion object {
//        val KEY_BACKGROUND = "background"
//        val KEY_NOTIFICATION_ID = "notificationId"
//        val KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION =
//            "com.uoa.android.telmatics.data.services.SENSORS_LOCATION_CHANGED"
//        val KEY_GET_NEW_SENSOR_DATA = "com.uoa.android.telmatics.data.services.SENSOR_DATA_CHANGED"
//        val KEY_NOTIFICATION_STOP_ACTION =
//            "com.uoa.android.telmatics.data.services.NOTIFICATION_STOP"
//        val KEY_STOP_SERVICE = "com.uoa.android.telmatics.data.services.STOP_SERVICE"
//        val KEY_START_SERVICE = "com.uoa.android.telmatics.data.services.START_SERVICE"
//        val SENSORDID = "SENSORID"
//        val ACCELEROM = "ACCELEROMETER"
//        val ACCELEROMLTDA = "ACCELEROMETERLTDA"
//        val ACCELEROMLTDAUN = "ACCELEROMETER_LTD_A_UN"
//        val ACCELEROM_ACC = "ACCELEROM_ACC"
//        val GYRO = "GYRO"
//        val GYROLTDA = "GYRO_LTDA"
//        val GYROLTDAUN = "GYRO_LTDA_UN"
//        val GYRO_ACC = "GYRO_ACC"
//        val GRAVITY = "GRAVITY"
//        val GRAVITY_ACC = "GRAVITY_ACC"
//        val LIN_ACCELEROM = "LIN_ACCELEROM"
//        val LIN_ACCELEROM_ACC = "LIN_ACCELEROM_ACC"
//        val MAGNETO = "MAGNETO"
//        val MAGNETO_ACC = "MAGNETO"
//        val PROXIMITY = "PROXIMITY"
//        val PROXIMITYACC = "PROXIMITY_ACC"
//        val ROTVECT = "ROTATIONAL_VECTOR"
//        val ROTVECTACC = "ROTATIONAL_VECTOR_ACC"
//        val YAW = "YAW"
//        val PITCH = "PITCH"
//        val ROLL = "ROLL"
//        val LOC_TIME = "LOCATION_TIME"
//        val COLLECTED_DATE = "COLLECTED_DATE"
//        val TrackPOINT = "TrackPoint"
//        val TRACK = "Track"
//        val LASTPOINTS = "LastKnownPoints"
//        val LONGITUDE = "longitude"
//        val LATITUDE = "latitude"
//        val SENSORSDATA = "sensorsData"
//        val DISTANCE = "totalMeters"
//        val SPEED = "speed"
//        val LOC_ACCURACY = "accuracy"
//
//    }
//}