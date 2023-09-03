package com.uoa.telmaticsapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrackViewModel: ViewModel() {
    private var sbText= MutableLiveData<String>("Start Trip")
    val btnText: LiveData<String>
        get() = sbText

    private var deviceToken=MutableLiveData<String>("0")
    val dToken: LiveData<String>
        get() = deviceToken

    fun setDeviceToken(devToken:String){
        deviceToken.value=devToken
    }


    private var tripId=MutableLiveData<String>("0")
    val tripID: LiveData<String>
        get()=tripId

    private var dState= MutableLiveData<String>("0")
    val drinkState: LiveData<String>
        get()=dState

    private var overloading=MutableLiveData<String>("0")
    val ovLoading: LiveData<String>
        get()=overloading

    private var other_experiences=MutableLiveData<String>("0")
    val oExp: LiveData<String>
        get()=other_experiences

    fun storeDrinkStateLabel(dStatus:String){
        dState.value=dStatus
    }

    fun saveOverloadingLabel(oLoading:String){
        overloading.value=oLoading
    }
    fun saveOExperience(oExperience:String){
        other_experiences.value=oExperience
    }

    fun saveTripId(tId:String){
        tripId.value=tId
    }

}