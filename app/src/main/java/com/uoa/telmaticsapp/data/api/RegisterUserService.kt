package com.uoa.telmaticsapp.data.api

import com.google.gson.annotations.SerializedName
import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.data.model.UserAPIResponse
import retrofit2.Response
import retrofit2.http.POST

interface RegisterUserService {
//    @POST("v1/Registration/create")
    suspend fun registerUser()
}