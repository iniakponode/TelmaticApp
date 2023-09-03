package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository


class DeleteSensorData(private val sensorDRepo: SensorDataRepository) {
    suspend fun execute(sensorData: SensorsData)=sensorDRepo.deleteSensorData(sensorData)
}