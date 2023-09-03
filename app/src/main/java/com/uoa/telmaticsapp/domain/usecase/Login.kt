package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.UserAPIResponse
import com.uoa.telmaticsapp.domain.repository.UserRepository
import com.uoa.telmaticsapp.data.util.Resource

class Login(private  val loginRepo:UserRepository ) {
    suspend fun execute(loginQuery:String):Resource<UserAPIResponse>{
        return loginRepo.loginUser(loginQuery)
    }
}