//package com.uoa.telmaticsapp.presentation.viewModel
//
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.uoa.telmaticsapp.util.TrackingSensor
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class LightSensorViewModel @Inject constructor(
//    private val lightSensor: TrackingSensor
//): ViewModel(){
//            var isDark by mutableStateOf(false)
//
//    init {
//        lightSensor.startlisteningToSensor()
//        lightSensor.setOnSensorValuesChangedListener {
//            values->
//            val lux=values[0]
//            isDark=lux<60f
//        }
//    }
//}