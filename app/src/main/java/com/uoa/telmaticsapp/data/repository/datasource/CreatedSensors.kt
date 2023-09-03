package com.uoa.telmaticsapp.data.repository.datasource

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.uoa.telmaticsapp.util.AndroidSensor
import javax.inject.Inject

class LightSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType= Sensor.TYPE_LIGHT)

class LinearAcceleration @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType=Sensor.TYPE_LINEAR_ACCELERATION)

class AccelerometerSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType=Sensor.TYPE_ACCELEROMETER)

class AccelerometerSensorLtdAUnCalibrated @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED,
    sensorType=Sensor.TYPE_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED)

class AccelerometerSensorLtdA @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES,
    sensorType=Sensor.TYPE_ACCELEROMETER_LIMITED_AXES)

class GyroscopeSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_GYROSCOPE,
    sensorType=Sensor.TYPE_GYROSCOPE)

class GyroscopeSensorLtdA @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_GYROSCOPE_LIMITED_AXES,
    sensorType=Sensor.TYPE_GYROSCOPE_LIMITED_AXES)

class GyroscopeSensorLtdAUncalibrated @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_GYROSCOPE_LIMITED_AXES_UNCALIBRATED,
    sensorType=Sensor.TYPE_GYROSCOPE_LIMITED_AXES_UNCALIBRATED)

class GravitySensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= "Gravity",
    sensorType=Sensor.TYPE_GRAVITY)

class RotationVectorSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature="Rotation_Vecotor",
    sensorType=Sensor.TYPE_ROTATION_VECTOR)
class MagnetometerSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature="Magnetometer_Sensor",
    sensorType=Sensor.TYPE_MAGNETIC_FIELD)
class LocationSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_LOCATION,
    sensorType=Sensor.TYPE_ALL)

class GPSLocationSensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_LOCATION_GPS,
    sensorType=Sensor.TYPE_ALL)

class ProximitySensor @Inject constructor(
    context: Context
): AndroidSensor(context=context,
    sensorFeature= PackageManager.FEATURE_SENSOR_PROXIMITY,
    sensorType=Sensor.TYPE_PROXIMITY)
