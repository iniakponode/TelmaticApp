package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.UserAPIResponse
import com.uoa.telmaticsapp.domain.repository.UserRepository
import com.uoa.telmaticsapp.data.util.Resource

class RegisterUser(private val registerUser: UserRepository) {
    suspend fun execute():Resource<UserAPIResponse> {
        return registerUser.RegisterUser()
    }
}