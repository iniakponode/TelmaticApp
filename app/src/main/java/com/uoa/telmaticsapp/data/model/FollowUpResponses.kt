package com.uoa.telmaticsapp.data.model

import com.google.gson.annotations.SerializedName

data class FollowUpResponses(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("respId")
    val tripId: String,
    @SerializedName("other_experiences")
    val other_experiences: String,
    @SerializedName("drunk_state")
    val drunk_state: String,
    @SerializedName("overloading")
    val overloading:String,
    @SerializedName("dateTime")
    val dateTime: String

    )
