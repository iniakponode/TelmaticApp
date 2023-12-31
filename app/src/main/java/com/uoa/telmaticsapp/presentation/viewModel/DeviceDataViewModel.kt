package com.uoa.telmaticsapp.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.domain.usecase.CreateUser
import com.uoa.telmaticsapp.domain.usecase.UpdateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceDataViewModel@Inject constructor(
//    private val app:Application,
    private val createUser:CreateUser,
    private val updateUser: UpdateUser,
    application: Application
): AndroidViewModel(application){

    private var sbText= MutableLiveData<String>("Register Device")
    val btnText: LiveData<String>
        get() = sbText

    private var c_id= MutableLiveData<String>("")

        get()=c_id

    private var f_name= MutableLiveData<String>("")

        get()=f_name

    private var l_Name= MutableLiveData<String>("")

        get()=l_Name

    private var n_Name= MutableLiveData<String>("")

        get()=n_Name

    private var u_phone= MutableLiveData<String>("")

        get() = u_phone

    private var u_email= MutableLiveData<String>("")

        get() = u_email

    private var u_address= MutableLiveData<String>("")
//    val address: LiveData<String>
        get() = u_address

    private var dob= MutableLiveData<String>("")
//    val birthday: LiveData<String>
        get() = dob

    private var u_gender= MutableLiveData<String>("")

        get() = u_gender

    private var m_status= MutableLiveData<String>("")
//    val maritalStatus: LiveData<String>
        get() = m_status

    private var n_child= MutableLiveData<Int>(0)
//    val noOfChildren: LiveData<Int>
        get() = n_child
    private var u_type= MutableLiveData<String>("")
//    val userType: LiveData<String>
        get() = u_type

fun updateSBText(regStatText:String){
    sbText.value=regStatText
}
//    fun saveDeviceID(deviceID:String){
//        c_id.value=deviceID
//    }
    fun InitializeUser(deviceModel: DeviceModel):Boolean{
        var inserted=false
        c_id.value=deviceModel.clientID
        f_name.value=deviceModel.firstName
        l_Name.value=deviceModel.lastName
        n_Name.value=deviceModel.nickname
        u_phone.value=deviceModel.phone
        u_email.value=deviceModel.email
        u_address.value=deviceModel.address
        dob.value=deviceModel.birthday
        u_gender.value=deviceModel.birthday
        m_status.value=deviceModel.maritalStatus
        n_child.value=deviceModel.childrenCount

        val newDeviceModel=DeviceModel(
            c_id.value!!,
            f_name.value!!,
            l_Name.value!!,
            n_Name.value!!,
            u_phone.value!!,
            u_email.value!!,
            u_address.value!!,
            dob.value!!,
            u_gender.value!!,
            m_status.value!!,
            n_child.value!!,
            u_type.value!!
        )

    if(!inserted){
               GlobalScope.launch(Dispatchers.IO) {
                   createUser.execute(newDeviceModel)
               }
               inserted=true
           }
        return inserted
    }

    fun updateUser(deviceModel: DeviceModel):Boolean{
        var updated=false
        c_id.value=deviceModel.clientID
        f_name.value=deviceModel.firstName
        l_Name.value=deviceModel.lastName
        n_Name.value=deviceModel.nickname
        u_phone.value=deviceModel.phone
        u_email.value=deviceModel.email
        u_address.value=deviceModel.address
        dob.value=deviceModel.birthday
        u_gender.value=deviceModel.birthday
        m_status.value=deviceModel.maritalStatus
        n_child.value=deviceModel.childrenCount

        val newDeviceModel=DeviceModel(
            c_id.value!!,
            f_name.value!!,
            l_Name.value!!,
            n_Name.value!!,
            u_phone.value!!,
            u_email.value!!,
            u_address.value!!,
            dob.value!!,
            u_gender.value!!,
            m_status.value!!,
            n_child.value!!,
            u_type.value!!)
        GlobalScope.launch(Dispatchers.IO) {
            updateUser.execute(newDeviceModel).also {
                updated=true
            }

        }
        return updated
    }
}