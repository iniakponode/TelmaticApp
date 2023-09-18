package com.uoa.telmaticsapp.data.model


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("Devicetoken")
    val devicetoken: String, // UUID
    @SerializedName("Password")
    val password: String // DeviceModel Group Instance Key
)