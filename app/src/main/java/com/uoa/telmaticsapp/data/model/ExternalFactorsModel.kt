package com.uoa.telmaticsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = ExternalFactorsModel.TABLE_NAME,
    indices=[Index(ExternalFactorsModel.TRACKID)]
)
data class ExternalFactorsModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TRACKID)
    @SerializedName(TRACKID)
    val trackID: String,
    @SerializedName("StartDate")
    val startDate: String, // 2022-11-09T17:17:33 +0100
    @SerializedName("EndDate")
    val endDate: String, // 2022-11-09T17:29:42 +0100

    @SerializedName("other_experiences")
    val other_experiences: String,
    @SerializedName("drunk_state")
    val drunk_state: String,
    @SerializedName("overloading")
    val overloading:String,
)
{
    companion object {
        const val TRACKID="trackId"
        const val TABLE_NAME="track"
    }
}