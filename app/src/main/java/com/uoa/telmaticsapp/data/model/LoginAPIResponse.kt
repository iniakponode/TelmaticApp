package com.uoa.telmaticsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "loginAPIResponse",
    indices=[Index("loginId")]
)
data class LoginAPIResponse(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name ="loginId")
    @SerializedName("loginId")
    val loginID: String,
    @SerializedName("UserId")
    val userId:String,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
    )
