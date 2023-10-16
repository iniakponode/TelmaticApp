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
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROM
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROMLTDA
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROMLTDAUN
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROM_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.COLLECTED_DATE
import com.uoa.telmaticsapp.data.services.Constants.Companion.GRAVITY
import com.uoa.telmaticsapp.data.services.Constants.Companion.GRAVITY_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYRO
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYROLTDA
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYROLTDAUN
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYRO_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_BACKGROUND
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_NOTIFICATION_ID
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_NOTIFICATION_STOP_ACTION
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_STOP_SERVICE
import com.uoa.telmaticsapp.data.services.Constants.Companion.LIN_ACCELEROM
import com.uoa.telmaticsapp.data.services.Constants.Companion.LIN_ACCELEROM_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.LOC_TIME
import com.uoa.telmaticsapp.data.services.Constants.Companion.MAGNETO
import com.uoa.telmaticsapp.data.services.Constants.Companion.MAGNETO_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.PITCH
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROLL
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROTVECT
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROTVECTACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.SENSORDID
import com.uoa.telmaticsapp.data.services.Constants.Companion.YAW
import com.uoa.telmaticsapp.domain.usecase.StartSensors
import com.uoa.telmaticsapp.domain.usecase.StopSensors
import com.uoa.telmaticsapp.presentation.ui.AppStartHome
import com.uoa.telmaticsapp.presentation.ui.StoredToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class SensorService : Service() {

    private lateinit var locationManager: LocationManager
    private var background = false
    private val notificationId = 1

    @Inject
    lateinit var startSensors: StartSensors

    @Inject
    lateinit var stopSensors: StopSensors

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate() {
        super.onCreate()
        val notification = createNotification(
            getString(R.string.not_available),
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
                "Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            // Configure the notification channel.
            notificationChannel.enableLights(false)
            notificationChannel.setSound(null, null)
            notificationChannel.enableVibration(false)
            notificationChannel.vibrationPattern = longArrayOf(0L)
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val contentIntent = PendingIntent.getActivity(
            this,
            notificationId,
            Intent(this, AppStartHome::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val stopNotificationIntent = Intent(this, ActionListener::class.java)
        stopNotificationIntent.action = KEY_NOTIFICATION_STOP_ACTION
        stopNotificationIntent.putExtra(KEY_NOTIFICATION_ID, notificationId)
        val pendingStopNotificationIntent =
            PendingIntent.getBroadcast(this, notificationId, stopNotificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(baseContext, application.packageName)
            .setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Tracking Started")
            .setWhen(System.currentTimeMillis())
            .setDefaults(0)
            .setVibrate(longArrayOf(0L))
            .setSound(null)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(contentIntent)
            .addAction(
                R.mipmap.ic_launcher_round,
                getString(R.string.stop_notifications),
                pendingStopNotificationIntent
            )

        return notificationBuilder.build()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
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

    override fun onDestroy() {
        super.onDestroy()
        val sensorServiceIntent = Intent()
        sensorServiceIntent.action = KEY_STOP_SERVICE
        sensorServiceIntent.putExtra("STOP_SENSORS", "Stopping Sensors Service......")
        sensorServiceIntent.putExtra(KEY_NOTIFICATION_ID, -1)
        stopSelf()
    }

    private suspend fun startSensors() {
        val sensorsDataResult = startSensors.execute()
        Log.i("Size", sensorsDataResult.toString())
        val time = System.currentTimeMillis()
        val loc_date = Date(time)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dat_text: String = sdf.format(loc_date)

        var sensors_D = SensorsModel(
            StoredToken.tripId,
            listOf(0.0f, 0.0f, 0.0f),
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
            0,
            listOf(0.0f, 0.0f, 0.0f),
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

        sensorsDataResult.collect { sensorsDataResult ->
            sensorsDataResult.forEach { sensor ->
                val intent = Intent()
                intent.putExtra(SENSORDID, sensor.sensorDataID.toString())
                intent.putExtra(ACCELEROM, sensor.accelerom.toFloatArray())
                intent.putExtra(ACCELEROMLTDAUN, sensor.acceleromltdAUncalibrated.toFloatArray())
                intent.putExtra(ACCELEROM_ACC, sensor.accelerAcc)
                intent.putExtra(GRAVITY, sensor.gravity.toFloatArray())
                intent.putExtra(GRAVITY_ACC, sensor.gravityAcc)
                intent.putExtra(GYRO, sensor.gyroscope.toFloatArray())
                intent.putExtra(GYROLTDAUN, sensor.gyroscopeLtdAUncalibrated.toFloatArray())
                intent.putExtra(GYRO_ACC, sensor.gyroscopeAcc)
                intent.putExtra(LIN_ACCELEROM, sensor.linearAcceleration.toFloatArray())
                intent.putExtra(LIN_ACCELEROM_ACC, sensor.linAccelerAcc)
                intent.putExtra(MAGNETO, sensor.magnetometer.toFloatArray())
                intent.putExtra(MAGNETO_ACC, sensor.magnetometerAcc)
                intent.putExtra(ROTVECT, sensor.RotVect.toFloatArray())
                intent.putExtra(ROTVECTACC, sensor.rotVectAcc)
                intent.putExtra(COLLECTED_DATE, sensor.date)
                intent.putExtra(YAW, sensor.yaw)
                intent.putExtra(PITCH, sensor.pitch)
                intent.putExtra(ROLL, sensor.roll)
                intent.putExtra(LOC_TIME, dat_text)
                intent.action = KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
            }
        }

    }
    class ActionListener : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null && intent.action != null) {
                if (intent.action.equals(KEY_NOTIFICATION_STOP_ACTION) || intent.action.equals(
                        KEY_STOP_SERVICE
                    )
                ) {
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