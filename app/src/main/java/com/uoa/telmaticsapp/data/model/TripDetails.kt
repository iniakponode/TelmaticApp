package com.uoa.telmaticsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = TripDetails.TABLE_NAME,
    indices=[Index(TripDetails.TRIPDID)]
)
data class TripDetails(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name ="tripId")
    @SerializedName("TipId")
    val tripID: String,
    @SerializedName(TRIPDID)
    val trackId: String
){
    companion object{
        const val  TABLE_NAME="trip_details"
        const val  TRIPDID="trackId"
    }
}