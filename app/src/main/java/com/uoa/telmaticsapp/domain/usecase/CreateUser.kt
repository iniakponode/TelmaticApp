package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.domain.repository.UserRepository

class CreateUser(private val userRepo: UserRepository) {
    suspend fun execute(user: User)=userRepo.saveUser(user)
}