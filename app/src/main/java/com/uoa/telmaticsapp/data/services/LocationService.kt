package com.uoa.telmaticsapp.data.services

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.data.util.LocationMethods
import com.uoa.telmaticsapp.presentation.ui.AppStartHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class LocationService : Service(), LocationListener {
    var backgound=false
    private val notificationActivityRequestCode = 1
    private val notificationId = 2
    private lateinit var locationManager: LocationManager
    private val notificationStopRequestCode = 3
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        val notification = createNotification(getString(R.string.not_available),
            "Tracking Location"
        )
        locationManager=getSystemService(LOCATION_SERVICE) as LocationManager
        startForeground(notificationId, notification)
    }

    private fun createNotification(direction: String, message: String): Notification {

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                application.packageName,
                "LocationService", NotificationManager.IMPORTANCE_DEFAULT
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
        // Stop notification intent
        val stopNotificationIntent = Intent(this, ActionListener::class.java)
        stopNotificationIntent.action = SensorService.KEY_NOTIFICATION_STOP_ACTION
        stopNotificationIntent.putExtra(SensorService.KEY_NOTIFICATION_ID, notificationId)
        val pendingStopNotificationIntent =
            PendingIntent.getBroadcast(this, notificationStopRequestCode, stopNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Tracking Location Started")
            .setWhen(System.currentTimeMillis())
            .setDefaults(0)
            .setVibrate(longArrayOf(0L))
            .setSound(null)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(contentIntent)
            .addAction(R.mipmap.ic_launcher_round, getString(R.string.stop_notifications), pendingStopNotificationIntent)


        return notificationBuilder.build()
    }



    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
                backgound=it.getBooleanExtra(SensorService.KEY_BACKGROUND,false)
//            Log.i("Intent2","Intent 2 Started")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0f,this)
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0f,this)

//            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//            stopSelf()
        }

        return START_STICKY
    }
    @Suppress("DEPRECATION")
    override fun onLocationChanged(location: Location) {
        LocationMethods.makeUseOfNewLocation(location)
        var currentBestLocation = LocationMethods.currentBestLocation
        GlobalScope.launch(Dispatchers.IO) {
            if (currentBestLocation == null) {
                currentBestLocation = location
            }
//            val sensors_D=startSensors()
            val latitude = currentBestLocation!!.latitude
            val longitude = currentBestLocation!!.longitude
            val distance = currentBestLocation!!.distanceTo(currentBestLocation!!)
            val speed = currentBestLocation!!.speed
            val accuracy = currentBestLocation!!.accuracy
            val time = currentBestLocation!!.time
            val loc_date = Date(time)
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val dat_text: String = sdf.format(loc_date)

            if (backgound) {
                val notification = createNotification(distance.toString(), dat_text)
                startForeground(notificationId, notification)
            } else {
                this@LocationService.stopForeground(true)
            }

            val intent = Intent(applicationContext, AppStartHome::class.java)
            intent.putExtra(SensorService.LATITUDE, latitude)
            intent.putExtra(SensorService.LONGITUDE, longitude)
//            intent.putExtra("loc_lat", currentBestLocation!!.latitude)
//            intent.putExtra("loc_long", currentBestLocation!!.longitude)
            intent.putExtra(SensorService.SPEED, speed)
            intent.putExtra(SensorService.LOC_ACCURACY, accuracy)
            intent.putExtra(SensorService.DISTANCE, distance)
            intent.putExtra(SensorService.LOC_TIME, dat_text)
            intent.action = SensorService.KEY_LOCATION_VALUE_CHANGED
//            Log.i("LATITUDE", latitude.toString())
//            Log.i("LONG", longitude.toString())
            Log.i("LocAcc",accuracy.toString())
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val locationServiceIntent=Intent()
        locationServiceIntent.action= SensorService.KEY_STOP_SERVICE
        locationServiceIntent.putExtra("STOP_LOC_TRACKING","Stopping location Service......")
        locationServiceIntent.putExtra(SensorService.KEY_NOTIFICATION_ID,-1)
        stopSelf()

    }

    class ActionListener : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            if (intent != null && intent.action != null) {
                if (intent.action.equals(SensorService.KEY_NOTIFICATION_STOP_ACTION)||intent.action.equals(
                        SensorService.KEY_STOP_SERVICE
                    )) {
                    context?.let {
                        val notificationManager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val sensorsIntent = Intent(context, LocationService::class.java)
                        context.stopService(sensorsIntent)
                        val notificationId = intent.getIntExtra(SensorService.KEY_NOTIFICATION_ID, -1)
                        if (notificationId != -1) {
                            notificationManager.cancel(notificationId)
                        }
                    }
                }
            }
        }
    }
}