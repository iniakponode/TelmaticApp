package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import java.util.*
import java.util.concurrent.Flow

class StartSensors (private val sensorDataRepo: SensorDataRepository){

          fun execute() = sensorDataRepo.getSensorDataFromHardware()

}