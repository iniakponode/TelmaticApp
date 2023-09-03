package com.uoa.telmaticsapp.domain.usecase

import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StopSensors(private val sensorDataRepo: SensorDataRepository) {
    fun execute(){
        GlobalScope.launch {
            sensorDataRepo.stopSensor()
        }

    }
}