package com.uoa.telmaticsapp.data.repository.datasourceImpl

import com.telematicssdk.auth.TelematicsAuth
import com.telematicssdk.auth.api.model.Gender
import com.telematicssdk.auth.external.SuccessListener
import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import java.util.*

class UserRemoteDataSourceImpl(){
    val Private_Drivers_INSTANCE_ID="abe10ef5-0cfb-4e3a-9702-859a96ec5abc"
    val Private_Drivers_INSTANCE_Key="839f0179-79b8-48c8-9a75-e55ba7b64aaf"

    val Public_Drivers_INSTANCE_ID="f4045fcf-43ce-4343-b36e-a01000d15019"
    val Public_Drivers_INSTANCE_Key="c35e5ee4-3d11-4a3a-be09-ad7393362bdb"
    lateinit var userAPIResponse: UserAPIResponse
    val newDeviceModel=DeviceModel(
        clientID = "",
        firstName = "",
        lastName = "",
        nickname = "",
        phone = "",
        email = "",
        address = "",
        birthday = "",
        gender = "",
        maritalStatus = "",
        childrenCount = 0,
        userType =""

    )

    }