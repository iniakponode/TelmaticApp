package com.uoa.telmaticsapp.data.model


import androidx.room.*

import com.google.gson.annotations.SerializedName
import com.uoa.telmaticsapp.data.model.Track.Companion.TRACKID
import java.util.*

@Entity(tableName = TrackPoint.TABLE_NAME,
    indices=[Index(TrackPoint.POINTID)]
)
data class TrackPoint(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = POINTID)
    @SerializedName(POINTID)
    val pointID:String,
    @ColumnInfo(name = TRACKID)
    @SerializedName(TRACKID)
    val trackId:String,
    @SerializedName("Number")
    val number:Int, // 0.042920490531260326
    @SerializedName("Acceleration")
    val acceleration: Float, // 0.042920490531260326

    @SerializedName("AccelerationXOriginal")
    val accelerationXOriginal: Float, // 0.4210500121116638
    @SerializedName("AccelerationYOriginal")
    val accelerationYOriginal: Float, // 2.182950019836426
    @SerializedName("AccelerationZOriginal")
    val accelerationZOriginal: Float, // 9.631500244140625
    @SerializedName("AcceleAcc")
    val acceleAccOriginal: Int, // 0.22252293780187826
//    @SerializedName("Accuracy")
//    val accuracy: Double, // 77.6
//    @SerializedName("Course")
//    val course: Double, // 0.0
    @SerializedName("Deceleration")
    val deceleration: Double, // 0.4210500121116638
//    @SerializedName("DeviceBlocked")
//    val deviceBlocked: Boolean, // false
    @SerializedName("timeStamp")
    val timeStamp: String, // false
//    @SerializedName("EstablishedIndexA")
//    val establishedIndexA: Boolean, // false
//    @SerializedName("EstablishedIndexB")
//    val establishedIndexB: Boolean, // false
    @SerializedName("GyroscopeXOriginal")
    val gyroscopeXOriginal: Float, // 0.0
    @SerializedName("GyroscopeYOriginal")
    val gyroscopeYOriginal: Float, // 0.0
    @SerializedName("GyroscopeZOriginal")
    val gyroscopeZOriginal: Float, // 0.0
    @SerializedName("GyroscopeAcc")
    val gyroscopeAccOriginal: Int, // 0.0

    @SerializedName("GravitXOriginal")
    val gravityXOriginal: Float, // 0.9818043062324796
    @SerializedName("GravitYOriginal")
    val gravityYOriginal: Float, // 0.9818043062324796
    @SerializedName("GravityZOriginal")
    val gravityZOriginal: Float, // 0.9818043062324796
    @SerializedName("gravityAcc")
    val gravityAccOriginal:Int,

    @SerializedName("LinearAccelerationXOriginal")
    val linearAccelerationXOriginal:Float, // 0.9818043062324796
    @SerializedName("LinearAccelerationYOriginal")
    val linearAccelerationYOriginal:Float, // 0.9818043062324796
    @SerializedName("LinearAccelerationZOriginal")
    val linearAccelerationZOriginal:Float, // 0.9818043062324796
    @SerializedName("linAcc")
    val linAccOriginal:Int,

    @SerializedName("MagnetometerXOriginal")
    val magnetometerXOriginalOriginal:Float, // 0.9818043062324796
    @SerializedName("MagnetometerYOriginal")
    val magnetometerYOriginalOriginal:Float, // 0.9818043062324796
//    @SerializedName("MagnetometerZOriginal")
//    val magnetometerZOriginalOriginal:Float, // 0.9818043062324796
    @SerializedName("magnetoAcc")
    val magnAccOriginal:Int,

//    @SerializedName("ProximityXOriginal")
//    val proximityXOriginalOriginal:Float, // 0.9818043062324796
//    @SerializedName("ProximityYOriginal")
//    val proximityYOriginal:Float, // 0.9818043062324796
//    @SerializedName("ProximityZOriginal")
//    val proximityZOriginal:Float, // 0.9818043062324796
//    @SerializedName("proximityAcc")
//    val proximityAccOriginal:Int,

    @SerializedName("RotVectorXOriginal")
    val rotVectorXOriginal:Float, // 0.9818043062324796
    @SerializedName("RotVectorYOriginal")
    val rotVectorYOriginal:Float, // 0.9818043062324796
    @SerializedName("RotVectorZOriginal")
    val rotVectorZOriginal:Float, // 0.9818043062324796
    @SerializedName("rotAcc")
    val rotVectAccOriginal:Int,
//    @SerializedName("Height")
//    val height: Double, // 45200.0
//    @SerializedName("Lateral")
//    val lateral: Double, // 0.0
    @SerializedName("Latitude")
    val latitude: String, // 4.914656
    @SerializedName("Longitude")
    val longitude: String, // 6.2602659
    @SerializedName("MidSpeed")
    val midSpeed: Double, // 0.0
    @SerializedName("PointDate")
    val pointDate: String, // 2022-11-09T17:17:33+0100
    @SerializedName("Speed")
    val speed: Double, // 0.0
    @SerializedName("TickTimestamp")
    val tickTimestamp: Int, // 1668010653
    @SerializedName("TotalMeters")
    val totalMeters: Double, // 0.0
    @SerializedName("Yaw")
//    val yaw: Float, // 0.0
//    @SerializedName("Pitch")
//    val pitch: Float, // 0.0
//    @SerializedName("Roll")
//    val roll: Float, // 0.0
    val yawFrmRotVector: Double,
    @SerializedName("Pitch")
    val pitchFrmRotVector: Double,

    @SerializedName("Roll")
    val rollFrmRotVector: Double,

) {
    companion object {
        const val POINTID = "pointId"
        const val TABLE_NAME = "trackpoint"
    }
}
