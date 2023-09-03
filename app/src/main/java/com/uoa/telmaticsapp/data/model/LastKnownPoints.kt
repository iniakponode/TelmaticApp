package com.uoa.telmaticsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName ="lastKPoints",
    indices=[Index("id")]
)
data class LastKnownPoints(
//    @SerializedName("accuracy")
//    val accuracy: String, // 1.5329999923706055
    @PrimaryKey
    @SerializedName("id")
    val id: String, // 1
    @SerializedName(Track.TRACKID)
    val lasttrackId: String,
    @SerializedName("latitude")
    val latitude: Double, // 4.910524
    @SerializedName("longitude")
    val longitude: Double, // 6.2573632
    @SerializedName("point_date")
    val pointDate: String, // 2022-11-13T16:23:31+0100
    @SerializedName("point_origin")
    val StartTrackId: String, // Track
)