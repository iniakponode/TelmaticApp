package com.uoa.telmaticsapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = LawEstablishingRule.TABLE_NAME,
    indices=[Index(LawEstablishingRule.LAWESTABRULEID)]
)
data class LawEstablishingRule(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = LawEstablishingRule.LAWESTABRULEID)
    @SerializedName(LAWESTABRULEID)
    val lawEstabRuleID: String,
    @SerializedName("EsblishmentLaw")
    val esblishmentLaw: String, // Law Estabishing the Rule
    @SerializedName("EstablishmentDate")
    val establishmentDate: String, // Date Law was established
    @SerializedName("RuleID")
    val ruleID: String, // Rule ID from SafetyTips
    @SerializedName("Section")
    val section: String // Section of the Law Establishing the Rule
){
    companion object{
        const val LAWESTABRULEID="lawEstId"
        const val TABLE_NAME="lawEstRule"
    }
}
