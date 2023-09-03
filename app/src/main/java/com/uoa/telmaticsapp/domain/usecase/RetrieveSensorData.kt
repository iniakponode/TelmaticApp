package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import java.util.*

class RetrieveSensorData(private val sensorDataRepo: SensorDataRepository) {
     fun execute()=sensorDataRepo.getSensorDataFromHardware()
}