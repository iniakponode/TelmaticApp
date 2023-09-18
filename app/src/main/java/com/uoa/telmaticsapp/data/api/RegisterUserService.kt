package com.uoa.telmaticsapp.data.api

interface RegisterUserService {
//    @POST("v1/Registration/create")
    suspend fun registerUser()
}