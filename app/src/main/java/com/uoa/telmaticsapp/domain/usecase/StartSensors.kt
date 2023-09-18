package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.SensorDataRepository

class StartSensors (private val sensorDataRepo: SensorDataRepository){

          fun execute() = sensorDataRepo.getSensorDataFromHardware()

}