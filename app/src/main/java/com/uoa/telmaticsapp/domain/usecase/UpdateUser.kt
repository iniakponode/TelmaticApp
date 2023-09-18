package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.domain.repository.UserRepository

class UpdateUser(private val userRepo: UserRepository) {
    suspend fun execute(deviceModel: DeviceModel)=userRepo.updateUser(deviceModel)
}