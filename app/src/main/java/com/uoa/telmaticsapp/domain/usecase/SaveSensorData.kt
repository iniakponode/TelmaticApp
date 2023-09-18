package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository

class SaveSensorData(private val sensorDataRepo:SensorDataRepository) {
    suspend fun execute(sensorData: SensorsModel)=sensorDataRepo.saveSensorData(sensorData)

}