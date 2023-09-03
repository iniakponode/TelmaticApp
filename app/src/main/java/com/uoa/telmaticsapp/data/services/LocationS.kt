package com.uoa.telmaticsapp.data.services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.presentation.ui.AppStartHome
import java.util.*


/**
 * Created by deepshikha on 24/11/16.
 */
class LocationS : Service(), LocationListener {
    var isGPSEnable = false
    var isNetworkEnable = false
    var latitude = 0.0
    var longitude = 0.0
    private var background = false
    var locationManager: LocationManager? = null
    var location: Location? = null
    private val mHandler = Handler()
    private var mTimer: Timer? = null
    var notify_interval: Long = 1000
    var intent: Intent? = null
    private val notificationId = 2
    private val notificationActivityRequestCode = 1
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        intent?.let {
            background = it.getBooleanExtra(SensorService.KEY_BACKGROUND, false)
        }
       return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        mTimer = Timer()
        mTimer!!.schedule(TimerTaskToGetLocation(), 5, notify_interval)
        intent = Intent(SensorService.KEY_LOCATION_VALUE_CHANGED)
        val notification = createNotification(getString(R.string.not_available),
            "Tracking Location"
        )
        startForeground(notificationId, notification)
        //        fn_getlocation();
    }

    override fun onLocationChanged(location: Location) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
    @SuppressLint("MissingPermission")
    private fun fn_getlocation() {
        locationManager = applicationContext.getSystemService(LOCATION_SERVICE) as LocationManager
        isGPSEnable = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        isNetworkEnable = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (!isGPSEnable && !isNetworkEnable) {
        } else {
            if (isNetworkEnable) {
                location = null
                locationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000,
                    0f,
                    this
                )
                if (locationManager != null) {
                    location =
                        locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    if (location != null) {
                        Log.e("latitude", location!!.latitude.toString() + "")
                        Log.e("longitude", location!!.longitude.toString() + "")
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        fn_update(location!!)
                    }
                }
            }
            if (isGPSEnable) {
                location = null
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    0f,
                    this
                )
                if (locationManager != null) {
                    location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        Log.e("latitude", location!!.latitude.toString() + "")
                        Log.e("longitude", location!!.longitude.toString() + "")
                        latitude = location!!.latitude
                        longitude = location!!.longitude
                        fn_update(location!!)
                    }
                }
            }
        }
    }

    private inner class TimerTaskToGetLocation : TimerTask() {
        override fun run() {
            mHandler.post { fn_getlocation() }
        }
    }

    private fun fn_update(location: Location) {
        intent!!.putExtra("latutide", location.latitude.toString() + "")
        intent!!.putExtra("longitude", location.longitude.toString() + "")
        sendBroadcast(intent)
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
        // Stop notification intent
//        val stopNotificationIntent = Intent(this, AppStartHome::class.java)
//        stopNotificationIntent.action = KEY_NOTIFICATION_STOP_ACTION
//        stopNotificationIntent.putExtra(KEY_NOTIFICATION_ID, notificationId)
//        val pendingStopNotificationIntent =
//            PendingIntent.getBroadcast(this, notificationStopRequestCode, stopNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

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
//            .addAction(R.mipmap.ic_launcher_round, getString(R.string.stop_notifications), pendingStopNotificationIntent)


        return notificationBuilder.build()
    }
    companion object {
        var str_receiver = "telmaticApp.data.services.LocationS"
    }
}