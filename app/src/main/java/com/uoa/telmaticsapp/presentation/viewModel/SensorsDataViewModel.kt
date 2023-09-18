package com.uoa.telmaticsapp.presentation.viewModel

//import io.reactivex.annotations.SchedulerSupport.IO

import android.app.Application
import androidx.lifecycle.*
import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SensorsDataViewModel @Inject constructor(
    private val saveSensorData: SaveSensorData,
    private val stopSensors: StopSensors,
    private val deleteSensorData: DeleteSensorData,
   private val addPoint: AddPoint,
    private val addTrack: AddTrack,
    private val updateTrack:UpdateTrack,
    private val addLKP:AddLastKnownPoints,
    private val getComputedTrack: GetComputedTrack,
    private val getPointsFromHardware: GetPointsFromHardware,
    private val getLastKPFrmHarware: GetLastKPFromHardware,
    private val getLastKPoints: GetLasKPoints,
    private val app:Application
): AndroidViewModel(app) {
//    init {
//        viewModelScope.launch {
//            startService()
//        }
//    }


//    @Inject lateinit var sensorsWorkerFactory: SensorWorkerFactory
//    private  val sensorWorkManager=WorkManager.getInstance(app.applicationContext)

    private var sensorD=MutableLiveData<String>()
    val sensorsData: LiveData<String>
        get() = sensorD

    private var accelerom=MutableLiveData<FloatArray>()
//    val accelerometer: LiveData<FloatArray>
//        get() = accelerom

    private var acceleromX=MutableLiveData<Float>()
//    val accelerometerX: LiveData<Float>
//        get() = acceleromX

    private var acceleromY=MutableLiveData<Float>()
    val accelerometerY: LiveData<Float>
        get() = acceleromY

    private var acceleromZ=MutableLiveData<Float>()
    val accelerometerZ: LiveData<Float>
        get() = acceleromZ

//    private var acceleromLTDA=MutableLiveData<FloatArray>()
//    val accelerometerLTDA: LiveData<FloatArray>
//        get() = acceleromLTDA

//    private var acceleromLTDAUn=MutableLiveData<FloatArray>()
//    val accelerometerLTDAUn: LiveData<FloatArray>
//        get() = acceleromLTDAUn

//    private var acceleromAcc=MutableLiveData<Int>()
//    val accelerometerAcc: LiveData<Int>
//        get() = acceleromAcc


    private var grav=MutableLiveData<FloatArray>()
    val gravity: LiveData<FloatArray>
        get() = grav

//    private var gravA=MutableLiveData<Int>()
//    val gravityAcc: LiveData<Int>
//        get() = gravA

//    private var gyro=MutableLiveData<FloatArray>()
//    val gyroscope: LiveData<FloatArray>
//        get() = gyro

    private var gyroX=MutableLiveData<Float>()
    val gyroscopeX: LiveData<Float>
        get() = gyroX

    private var gyroY=MutableLiveData<Float>()
    val gyroscopeY: LiveData<Float>
        get() = gyroY

    private var gyroZ=MutableLiveData<Float>()
    val gyroscopeZ: LiveData<Float>
        get() = gyroZ

//    private var gyroLTDA=MutableLiveData<FloatArray>()
//    val gyroscopeLTDA: LiveData<FloatArray>
//        get() = gyroLTDA

//    private var gyroLTDAUn=MutableLiveData<FloatArray>()
//    val gyroscopeLTDAUn: LiveData<FloatArray>
//        get() = gyroLTDAUn

//    private var gyroAcc=MutableLiveData<Int>()
//    val gyroscopeAcc: LiveData<Int>
//        get() = gyroAcc

//    private var linAccelerom=MutableLiveData<FloatArray>()
//    val linAccelerometer: LiveData<FloatArray>
//        get() = linAccelerom

//    private var linAcceleromAcc=MutableLiveData<Int>()
//    val linAccelerometerAcc: LiveData<Int>
//        get() = linAcceleromAcc

    private var lat=MutableLiveData<Double>()
    val latitude: LiveData<Double>
        get() = lat

    private var long=MutableLiveData<Double>()
    val longitude: LiveData<Double>
        get() = long

//    private var magneto=MutableLiveData<FloatArray>()
//    val magnetometer: LiveData<FloatArray>
//        get() = magneto

//    private var magnetoAcc=MutableLiveData<Int>()
//    val magnetometerAcc: LiveData<Int>
//        get() = magnetoAcc

//    private var proxim=MutableLiveData<FloatArray>()
//    val proximity: LiveData<FloatArray>
//        get() = proxim

//    private var proximAcc=MutableLiveData<Int>()
//    val proximityAcc: LiveData<Int>
//        get() = proximAcc

//    private var rotVect=MutableLiveData<FloatArray>()
//    val rotVector: LiveData<FloatArray>
//        get() = rotVect

//    private var rotVectAcc=MutableLiveData<Int>()
//    val rotVectorAcc: LiveData<Int>
//        get() = rotVectAcc

    private var sbText=MutableLiveData<String>("Start Trip")
    val btnText: LiveData<String>
        get() = sbText

//    private val workManager=WorkManager.getInstance(app.applicationContext)
//    private val sensorsLiveData = MutableLiveData<SensorsModel>()


    fun getComputedTrack(trackId:String):ExternalFactorsModel{
        return getComputedTrack.execute(trackId)
    }
    fun getPointFromHardware(sensorData: SensorsModel,
                             totalMeters:Double,
                             deceleration:Double,
                             trackId: String,
                             speed:Double,
                             midSpeed:Double,
                             longitude:String,
                             latitude:String):TrackPoint{
        return getPointsFromHardware.execute(
                            sensorData,
                            totalMeters,
                            deceleration,
                            trackId,
                            speed,
                            midSpeed,
                            longitude,
                            latitude)
    }
    fun getLastKPFromHardwre(
//        accuracy: String,
        lastKPId:String,
        lastTrackId: String,
        latitude: Double,
        longitude: Double,
        pointDate: String,
        startTrackId: String
    ):LastKnownPoints{
        return getLastKPFrmHarware.execute(
//            accuracy,
            lastKPId,
            lastTrackId,
            latitude,
            longitude,
            pointDate,
            startTrackId
        )
    }
//    fun getLastKPoints(): List<LastKnownPoints> {
//        return getLastKPoints.execute()
//    }
    suspend fun saveTrack(externalFactorsModel: ExternalFactorsModel){
        addTrack.execute(externalFactorsModel)
    }
    suspend fun updateTrack(externalFactorsModel: ExternalFactorsModel){
        updateTrack.execute(externalFactorsModel)
    }
    suspend fun savePoint(trackPoint: TrackPoint){
        addPoint.execute(trackPoint)
    }
//    fun saveLong(longitude: Double){
//        long.value=longitude
//    }

//    fun saveLatitude(latitude: Double){
//        lat.value=latitude
//    }
    suspend fun saveLastKnownPoint(lastKP:LastKnownPoints){
        addLKP.execute(lastKP)
    }

    fun setBtnText(btText:String){
        sbText.value=btText
    }
    fun saveSensorDataToDB(sensorsModel: SensorsModel){
        viewModelScope.launch(Dispatchers.IO){
             saveSensorData.execute(sensorsModel)
        }
    }
//    (Dispatcher.IO)
//    fun deleteFromDB(sensorsData: SensorsModel){
//        viewModelScope.launch(Dispatchers.IO) {
//            deleteSensorData.execute(sensorsData)
//        }
//    }

    fun stopSensors() {
        stopSensors.execute()
//        app.applicationContext.stopService(intent)
//        sensorWorkManager.cancelAllWork()
    }

//    fun startSensors(intent:Intent, lifecycleOwner: LifecycleOwner?)
//    fun startSensors(intent:Intent){
//        val sensorsServicedata=data(app,SensorsService::class.java)
//        sensorsServicedata.putExtra("Started","Sensor Service Started")
//        app.startService(sensorsServicedata)
//        app.applicationContext.startService(intent)
//       val request = OneTimeWorkRequestBuilder<SensorsWorker>().build()
//       val wManager= WorkManager.getInstance(app.applicationContext)
//        wManager.enqueue(request)
//        wManager.getWorkInfoById(request.id).get()
//            Observer<List<WorkInfo>> { workStatus ->
//                if (workStatus[1].state.isFinished) {
//                    // here you asign data to ViewModel
//                    val workOutputData: Data = workStatus[0].outputData
//                    sensorD.value=workOutputData.getString("FIRST_KEY")
//                }
//            }



//        WorkManager.getInstance(app.applicationContext).getWorkInfoById(request.id)

//        sensorWorkManager.enqueue(OneTimeWorkRequest.from(SensorsWorker::class.java))
//    }

}