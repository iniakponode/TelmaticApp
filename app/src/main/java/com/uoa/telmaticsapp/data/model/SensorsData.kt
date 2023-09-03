package com.uoa.telmaticsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "sensor_data")
data class SensorsData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sensorDataID")
    @SerializedName("sensorDataId")
    val sensorDataID: String,
    @SerializedName("Accelerometer")
    val accelerom: List<Float>,
//    @SerializedName("AccelerometerA")
//    val acceleromltdA: List<Float>,
    @SerializedName("AccelerometerAUncalibrated")
    val acceleromltdAUncalibrated: List<Float>,
    @SerializedName("AccelerAcc")
    val accelerAcc: Int,
    @SerializedName("LinearAcceleration")
    val linearAcceleration: List<Float>,
    @SerializedName("LinAccelerAcc")
    val linAccelerAcc: Int,
    @SerializedName("Gyroscope")
    val gyroscope: List<Float>,
//    @SerializedName("GyroscopeLtdA")
//    val gyroscopeLtdA: List<Float>,
    @SerializedName("GyroscopeLtdA")
    val gyroscopeLtdAUncalibrated: List<Float>,
    @SerializedName("GyroscopeAcc")
    val gyroscopeAcc: Int,
    @SerializedName("Gravity")
    val gravity: List<Float>,
    @SerializedName("GravityAcc")
    val gravityAcc:Int,
    @SerializedName("Magnetometer")
    val magnetometer: List<Float>,
    @SerializedName("MagnetometerAcc")
    val magnetometerAcc:Int,
//    @SerializedName("Proximity")
//    val proximity: List<Float>,
//    @SerializedName("ProximityAcc")
//    val proximityAcc:Int,
    @SerializedName("RotVect")
    val RotVect: List<Float>,
    @SerializedName("RotVectAcc")
    val rotVectAcc: Int,
    @SerializedName("Yaw")
    val yaw: Float,
    @SerializedName("Pitch")
    val pitch: Float,

    @SerializedName("Roll")
    val roll: Float,
    @SerializedName("DateTime")
    val date: String
)