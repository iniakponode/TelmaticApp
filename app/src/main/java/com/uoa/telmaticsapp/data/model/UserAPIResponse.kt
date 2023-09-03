package com.uoa.telmaticsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = UserAPIResponse.TABLE_NAME,
    indices=[Index(UserAPIResponse.ID)]
)
data class UserAPIResponse(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UserAPIResponse.ID)
    @SerializedName(UserAPIResponse.ID)
    val trackID: String,
    @SerializedName("DeviceToken")
    val deviceToken: String?,
    @SerializedName("AccessToken")
    val accessToken: String?,
    @SerializedName("RefreshToken")
    val refreshToken: String?
){
    companion object {
        const val ID="Id"
        const val TABLE_NAME="userAPIResponse"
    }
}