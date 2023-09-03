package com.uoa.telmaticsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.util.UUIDUtil
import com.google.gson.annotations.SerializedName
import com.uoa.telmaticsapp.data.model.User.Companion.CLIENTID
import com.uoa.telmaticsapp.data.model.User.Companion.TABLE_NAME
import java.util.*
@Entity(tableName = TABLE_NAME,
    indices=[Index(CLIENTID)]
)

data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = CLIENTID)
    @SerializedName(CLIENTID)
    val clientID: String,
    @SerializedName("FirstName")
    val firstName: String, // Driver's firstname
    @SerializedName("LastName")
    val lastName: String, // Driver's last name
    @SerializedName("Nickname")
    val nickname: String, // Driver's nickname
    @SerializedName("Phone")
    val phone: String, // Driver's Phone
    @SerializedName("Email")
    val email: String, // Driver Email
    @SerializedName("Address")
    val address: String,
    @SerializedName("Birthday")
    val birthday: String,
    @SerializedName("Gender")
    val gender: String,
    @SerializedName("MaritalStatus")
    val maritalStatus: String,
    @SerializedName("ChildrenCount")
    val childrenCount: Int,
    @SerializedName("UserType")
    val userType: String


){
    companion object {
        const val CLIENTID="clientID"
        const val TABLE_NAME="user"
    }
}

