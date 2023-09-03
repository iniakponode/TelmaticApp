//package com.uoa.telmaticsapp.presentation.viewModel
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.uoa.telmaticsapp.data.services.SensorService
//import com.uoa.telmaticsapp.data.model.User
//import com.uoa.telmaticsapp.domain.repository.SensorDataRepository
//import com.uoa.telmaticsapp.domain.usecase.DeleteSensorData
//import com.uoa.telmaticsapp.domain.usecase.SaveSensorData
//import com.uoa.telmaticsapp.domain.usecase.StartSensors
//import com.uoa.telmaticsapp.domain.usecase.StopSensors
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class MySensorsViewModel(private val saveSensorData: SaveSensorData,
//                         private val stopSensors: StopSensors,
//                         private val startSensors: StartSensors,
//                         private val deleteSensorData: DeleteSensorData,
//                         private val sensorService: SensorService,
////    private val db:
//                         private val sensorDataRepo: SensorDataRepository,
//                         private val app: Application,): AndroidViewModel(app) {
//    private var sbText= MutableLiveData<String>("Register User")
//    val btnText: LiveData<String>
//        get() = sbText
//
//    private var c_id= MutableLiveData<String>("")
//    val clientID: LiveData<String>
//        get()=c_id
//
//    private var f_name= MutableLiveData<String>("")
//    val firstName: LiveData<String>
//        get()=f_name
//
//    private var l_Name= MutableLiveData<String>("")
//    val lastName: LiveData<String>
//        get()=l_Name
//
//    private var n_Name= MutableLiveData<String>("")
//    val nickName: LiveData<String>
//        get()=n_Name
//
//    private var u_phone= MutableLiveData<String>("")
//    val phone: LiveData<String>
//        get() = u_phone
//
//    private var u_email= MutableLiveData<String>("")
//    val email: LiveData<String>
//        get() = email
//
//    private var u_address= MutableLiveData<String>("")
//    val address: LiveData<String>
//        get() = u_address
//
//    private var dob= MutableLiveData<String>("")
//    val birthday: LiveData<String>
//        get() = dob
//
//    private var u_gender= MutableLiveData<String>("")
//    val gender: LiveData<String>
//        get() = u_gender
//
//    private var m_status= MutableLiveData<String>("")
//    val maritalStatus: LiveData<String>
//        get() = m_status
//
//    private var n_child= MutableLiveData<Int>(0)
//    val noOfChildren: LiveData<Int>
//        get() = n_child
//    private var u_type= MutableLiveData<String>("")
//    val userType: LiveData<String>
//        get() = u_type
//
//    fun updateSBText(regStatText:String){
//        sbText.value=regStatText
//    }
//    fun InitializeUser(user: User): Boolean{
//        var inserted=false
//        c_id.value=user.clientID
//        f_name.value=user.firstName
//        l_Name.value=user.lastName
//        n_Name.value=user.nickname
//        u_phone.value=user.phone
//        u_email.value=user.email
//        u_address.value=user.address
//        dob.value=user.birthday
//        u_gender.value=user.birthday
//        m_status.value=user.maritalStatus
//        n_child.value=user.childrenCount
//
//        val newUser= User(
//            c_id.value!!,
//            f_name.value!!,
//            l_Name.value!!,
//            n_Name.value!!,
//            u_phone.value!!,
//            u_email.value!!,
//            u_address.value!!,
//            dob.value!!,
//            u_gender.value!!,
//            m_status.value!!,
//            n_child.value!!,
//            u_type.value!!)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            createUser.execute(newUser).also {
//                inserted=true
//            }
//
//        }
//
//        return inserted
//    }
//
//    fun UpdateUser(user: User):Boolean{
//        var updated=false
//        c_id.value=user.clientID
//        f_name.value=user.firstName
//        l_Name.value=user.lastName
//        n_Name.value=user.nickname
//        u_phone.value=user.phone
//        u_email.value=user.email
//        u_address.value=user.address
//        dob.value=user.birthday
//        u_gender.value=user.birthday
//        m_status.value=user.maritalStatus
//        n_child.value=user.childrenCount
//
//        val newUser= User(
//            c_id.value!!,
//            f_name.value!!,
//            l_Name.value!!,
//            n_Name.value!!,
//            u_phone.value!!,
//            u_email.value!!,
//            u_address.value!!,
//            dob.value!!,
//            u_gender.value!!,
//            m_status.value!!,
//            n_child.value!!,
//            u_type.value!!)
//        GlobalScope.launch(Dispatchers.IO) {
//            UpdateUser.execute(newUser).also {
//                updated=true
//            }
//
//        }
//        return updated
//    }
//}