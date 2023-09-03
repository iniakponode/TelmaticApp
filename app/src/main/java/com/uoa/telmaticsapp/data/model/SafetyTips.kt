package com.uoa.telmaticsapp.data.model


import com.google.gson.annotations.SerializedName

data class SafetyTips(
    @SerializedName("Continent")
    val continent: String, // Continent where these rules apply
    @SerializedName("Country")
    val country: String, // Country Rule is coming from
    @SerializedName("Region")
    val region: String, // Region/State Rule is domiciled from
    @SerializedName("ExecutionBody")
    val executionBody: String, // The Body that implements the rules
//    @SerializedName("EstablishmentLaw")
//    val lawEstablishingRule: List<LawEstablishingRule>, // The Body that implements the rules
    @SerializedName("Rule")
    val rule: String, // Rule name e.g
    @SerializedName("Description")
    val description: String // Rule Description

)