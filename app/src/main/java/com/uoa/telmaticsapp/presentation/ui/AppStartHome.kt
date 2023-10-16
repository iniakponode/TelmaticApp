package com.uoa.telmaticsapp.presentation.ui

//import com.uoa.telmaticsapp.databinding.FragmentHomeBinding

import android.annotation.SuppressLint
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.data.model.LastKnownPoints
import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROM
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROMLTDAUN
import com.uoa.telmaticsapp.data.services.Constants.Companion.ACCELEROM_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.COLLECTED_DATE
import com.uoa.telmaticsapp.data.services.Constants.Companion.DISTANCE
import com.uoa.telmaticsapp.data.services.Constants.Companion.GRAVITY
import com.uoa.telmaticsapp.data.services.Constants.Companion.GRAVITY_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYRO
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYROLTDAUN
import com.uoa.telmaticsapp.data.services.Constants.Companion.GYRO_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_BACKGROUND
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION
import com.uoa.telmaticsapp.data.services.Constants.Companion.KEY_LOCATION_VALUE_CHANGED
import com.uoa.telmaticsapp.data.services.Constants.Companion.LATITUDE
import com.uoa.telmaticsapp.data.services.Constants.Companion.LIN_ACCELEROM
import com.uoa.telmaticsapp.data.services.Constants.Companion.LIN_ACCELEROM_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.LONGITUDE
import com.uoa.telmaticsapp.data.services.Constants.Companion.MAGNETO_ACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.PITCH
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROLL
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROTVECT
import com.uoa.telmaticsapp.data.services.Constants.Companion.ROTVECTACC
import com.uoa.telmaticsapp.data.services.Constants.Companion.SENSORDID
import com.uoa.telmaticsapp.data.services.Constants.Companion.SPEED
import com.uoa.telmaticsapp.data.services.Constants.Companion.YAW
import com.uoa.telmaticsapp.data.services.LocationService
import com.uoa.telmaticsapp.data.services.SensorService
import com.uoa.telmaticsapp.data.util.LocationMethods
import com.uoa.telmaticsapp.databinding.FragmentAppStartHomeBinding
import com.uoa.telmaticsapp.presentation.viewModel.SensorsDataViewModel
import com.uoa.telmaticsapp.util.StoreIDs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.streams.asSequence

@AndroidEntryPoint
class AppStartHome() : Fragment() {
    private lateinit var binding: FragmentAppStartHomeBinding
    private lateinit var triggerBtn:String
    private lateinit var sf: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    companion object{
         var lat=0.0
         var long=0.0
    }

//    private val StopSensors=com.uoa.telmaticsapp.domain.usecase.StopSensors()
    private val sensorsDataviewModel: SensorsDataViewModel by activityViewModels()


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ):
            View {
                        sf = activity?.getSharedPreferences("saved_device_token", AppCompatActivity.MODE_PRIVATE)!!
                        editor = sf.edit()
                        val devToken=StoredToken.getStoredID(sf,"saved_device_token")
//                        saveRawSensorData(sensorsData)
                        var counter=0
                        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_app_start_home, container,false)
                        binding.sDID.text = getString(R.string.No_Location)
                        binding.sensorsViewModel=sensorsDataviewModel
                        binding.tvDToken.text= "DeviceModel Token: $devToken"
                        sensorsDataviewModel.btnText.observe(requireActivity()){ status->
                            triggerBtn=status
                        }


//        val listOfWorkInfo=sensorsDataviewModel.sensorsDataWorkerInfo.observe(requireActivity()){
//
//        }

//        if (trigBtn=="Start Trip")
//            binding.sDID.text = "Your Starting Location is:"
//
//        if (trigBtn=="Stop Trip")
//            binding.sDID.text = "Trip Ended in this Location:"

//                                binding.sDID.text = "Your Current Location is:"
//        binding.sDID.text = "Your Current Location is "+fulladdress
//
//        binding.tvAccelerometerX.text = "City $city"
//        binding.tvGyroX.text = "City $fulladdress"
//        binding.tvAccelerometerY.text = "State $state"
//        binding.tvAccelerometerZ.text = "City $country"
//        binding.tvGyroX.setTextColor(4)
//        binding.sDID.text = "Your Current Location is not set yet, waiting...."
//                        triggerBtn=""

//        val addressList=LocationMethods.getAddress(requireContext(), 4.910545, 6.2572600000000005 )
//        if (addressList.isEmpty()||addressList.size==1)
//            binding.sDID.text = "Your Current Location is not set yet, waiting...."
//        else{
//            binding.sDID.text = "Your Current Location is:"
//            binding.tvAccelerometerX.text = "City ${addressList.get(0)}"
//            binding.tvGyroX.text = "City ${addressList.get(1)}"
////        binding.tvAccelerometerY.text = "State $state"
//        binding.tvAccelerometerZ.text = "City $country"
//        }

        binding.btnStartTrip.setOnClickListener {

                            Log.i("TrackBTn",triggerBtn)

                            if (triggerBtn == "Start Trip") {
                                    counter += 1

                                sensorsDataviewModel.setBtnText("Stop Trip")
                                sensorsDataviewModel.btnText.observe(requireActivity()){ status->
                                    triggerBtn=status
                                }
//                                LocationMethods.getAddress(requireContext(),triggerBtn, binding, AppStartHome.lat, AppStartHome.long )
                                binding.btnStartTrip.text=triggerBtn
                                saveTrack(counter)
                                startForegroundServiceForSensors(false)

                            }
                            else{
                                sensorsDataviewModel.setBtnText("Start Trip")
                                sensorsDataviewModel.btnText.observe(requireActivity()){ status->
                                    triggerBtn=status
                                }
//
                                binding.btnStartTrip.text=triggerBtn
                                val endDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
                                } else {
                                    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
                                    dateFormat.format(Date())
                                }
                                StoreIDs.storeEndDateLocally(requireContext(),endDate.toString(),editor)
                                updateTrack(
                                    endDate.toString(),
                                    "",
                                    "",
                                    ""
                                )
                                sensorsDataviewModel.stopSensors()
                                val loc=LocationService()
                                loc.stopSelf()

                                stopForegroundServiceForSensors()
                                findNavController().navigate(R.id.action_appstHome_to_drinkStateForm)

                            }
                        }

                        return binding.root
                }

    private val broadcastReceiver1: BroadcastReceiver = object : BroadcastReceiver() {
        var number=0
        override fun onReceive(context: Context, intent: Intent) {

//            Log.i("Intents",intent.action?.get(0).toString())
//            Toast.makeText(requireContext(),"Intent1 Recieved",Toast.LENGTH_LONG)
            number+=1
            if (intent.action.equals(KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION)){
                //            Sensors Data
                val sensorsID=intent.getStringExtra(SENSORDID)
                val accelerom=intent.getFloatArrayExtra(ACCELEROM)
//                val acceleromLTDA=intent.getFloatArrayExtra(SensorService.ACCELEROMLTDA)
                val acceleroltdaUn=intent.getFloatArrayExtra(ACCELEROMLTDAUN)
                val gravity=intent.getFloatArrayExtra(GRAVITY)
                val gyroscope=intent.getFloatArrayExtra(GYRO)
//                val gyroscopeLTDA=intent.getFloatArrayExtra(SensorService.GYROLTDA)
                val gyroscopeLTDAUN=intent.getFloatArrayExtra(GYROLTDAUN)
                val linAccelerom=intent.getFloatArrayExtra(LIN_ACCELEROM)
//                val magnetom=intent.getFloatArrayExtra(SensorService.MAGNETO)
//                val proxim=intent.getFloatArrayExtra(SensorService.PROXIMITY)
                val rotVect=intent.getFloatArrayExtra(ROTVECT)
                StoreIDs.storeSensorsIDLocally(requireActivity(),sensorsID.toString(),editor)
                val sensorsD= SensorsModel(
                    sensorsID!!,
                    listOf(accelerom?.get(0), accelerom?.get(1), accelerom?.get(2)) as List<Float>,
//                    listOf(acceleromLTDA!![0],acceleromLTDA!![1],acceleromLTDA!![2]),
                    listOf(acceleroltdaUn?.get(0), acceleroltdaUn?.get(1),
                        acceleroltdaUn?.get(2)
                    ) as List<Float>,
                    intent.getIntExtra(ACCELEROM_ACC,0),
                    listOf(linAccelerom?.get(0),linAccelerom?.get(1),linAccelerom?.get(2)) as List<Float>,
                    intent.getIntExtra(LIN_ACCELEROM_ACC,0),
                    gyroscope?.let { listOf(it[0], it[1], it[2]) } as List<Float>,
//                    listOf(gyroscopeLTDA!![0],gyroscopeLTDA!![1],gyroscopeLTDA!![2]),
                    listOf(gyroscopeLTDAUN!![0], gyroscopeLTDAUN[1], gyroscopeLTDAUN[2]),
                    intent.getIntExtra(GYRO_ACC,0),
                    listOf(gravity!![0], gravity[1], gravity[2]),
                    intent.getIntExtra(GRAVITY_ACC,0),
//                    listOf(magnetom!![0],magnetom!![1]),
                    listOf(0f,0f),
                    intent.getIntExtra(MAGNETO_ACC,0),
                    listOf(rotVect!![0],rotVect!![1],rotVect!![2]),
                    intent.getIntExtra(ROTVECTACC,0),
                    intent.getFloatExtra(YAW,0.0f),
                    intent.getFloatExtra(PITCH,0.0f),
                    intent.getFloatExtra(ROLL,0.0f),
                    intent.getStringExtra(COLLECTED_DATE)!!

                    )
//                Log.i("Sensors_DataX",sensorsD.accelerom[0].toString())
//                Log.i("Sensors_DataY",sensorsD.accelerom[1].toString())
//                Log.i("Sensors_DataZ",sensorsD.accelerom[2].toString())
                Log.i("SIntent1","Intent1 recieved")
                saveRawSensorData(sensorsD)
        //TrackPoint Data
                val speed=intent.getDoubleExtra(SPEED,0.0)
                val distance=intent.getDoubleExtra(DISTANCE,0.0)
                val midDistance=distance/number
                val trackId=StoredToken.getStoredID(sf,"saved_trackId")
//                val longitude=intent.getStringExtra(SensorService.LONGITUDE)
//                val latitude=intent.getStringExtra(SensorService.LATITUDE)

                val point=sensorsDataviewModel.getPointFromHardware(
                    sensorsD,distance,0.0,trackId,speed,midDistance, lat.toString(),long.toString()
                )
                savePoint(point)
                val sensorDID=sensorsID
            val acceleromX= accelerom?.get(0)
            val acceleromY= accelerom?.get(1)
            val acceleromZ= accelerom?.get(2)
//            var gyroX=intent.getIntExtra(SensorService.GYRO_ACC,0)
//            var gyroY=gyroscope[1]
//            var gyroZ=gyroscope[2]


            Log.i("SD",sensorDID.toString())
            Log.i("SDAccX",acceleromX.toString())
            Log.i("SDAccY",acceleromY.toString())
            Log.i("SDAccZ",acceleromZ.toString())



//                binding.sDID.text="SD: $sensorsID"
//                binding.tvAccelerometerX.text="ACCX: $acceleromX"
//                binding.tvAccelerometerY.text="ACCY: $acceleromY"
//                binding.tvAccelerometerZ.text="ACCX: $acceleromZ"
//
//                binding.tvGyroX.text="GyroX: $gyroX"
//                binding.tvGyroY.text="GyroX: $gyroY"
//                binding.tvGyroZ.text="GyroX: $gyroZ"
//                savePoint(point)
//                Last Known TrackPoint

            }
//            if(intent.action.equals(SensorService.KEY_LOCATION_VALUE_CHANGED)){
//
//
//
//                }
        }
    }


    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        var number=0
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {

//            Log.i("Intents",intent.action?.get(0).toString())
//            Toast.makeText(requireContext(),"Intent1 Recieved",Toast.LENGTH_LONG)
            number+=1
            if(intent.action.equals(KEY_LOCATION_VALUE_CHANGED)){
                val longitude=intent.getDoubleExtra(LONGITUDE,0.0)
                val latitude=intent.getDoubleExtra(LATITUDE,0.0)

                val pointDate=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
                } else {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
                    dateFormat.format(Date())
                }

//                val longitude=intent.getStringExtra(SensorService.LONGITUDE)
//                val latitude=intent.getStringExtra(SensorService.LATITUDE)
//                Toast.makeText(requireContext(),"Intent Recieved",Toast.LENGTH_LONG).show()
                lifecycleScope.launch {
                    if (LocationMethods.isOnline(requireContext())) {
                            val addressList = LocationMethods.getAddress(
                                requireContext(),
                                intent.getDoubleExtra(LATITUDE, 0.0),
                                intent.getDoubleExtra(LONGITUDE, 0.0)
                            )

                            if (addressList.isEmpty()) {
                                binding.sDID.text = "Your Current Location is not set yet, waiting...."
                            } else {
                                Log.i("Address", addressList.get(0))
                                binding.sDID.text = "Your Current Location is: ${addressList.get(1)}"
                            }

                        }
                    else{
                            binding.sDID.text = "Waiting for Internet Connection....."
                        }
                }

                Log.i("SIntent2","Intent2 recieved")
//                Log.i("LAT",latitude.toString())
//                Log.i("LONG",longitude.toString())
                                val lastKPoint=sensorsDataviewModel.getLastKPFromHardwre(
//                    intent.getStringExtra(SensorService.LOC_ACCURACY).toString(),
                    UUID.randomUUID().toString(),
                    StoredToken.getStoredID(sf,"saved_trackId"),
                                    latitude,
                                    longitude,
                    pointDate.toString(),
                    StoredToken.getStoredID(sf,"start_trackId")
                )

                saveLastKnownPoints(lastKPoint)
            }

        }
    }

    private fun startForegroundServiceForSensors(background: Boolean) {
        val sensorServiceIntent = Intent(requireContext(), SensorService::class.java)
        sensorServiceIntent.putExtra(KEY_BACKGROUND, background)
        ContextCompat.startForegroundService(requireContext(), sensorServiceIntent)
//Location Foreground Service
        val locationServiceIntent = Intent(requireContext(), LocationService::class.java)
        locationServiceIntent.putExtra(KEY_BACKGROUND, background)
        lifecycleScope.launch {
            ContextCompat.startForegroundService(requireContext(), locationServiceIntent)
        }
    }

   private fun stopForegroundServiceForSensors(){
       sensorsDataviewModel.setBtnText("Stop Trip")
       sensorsDataviewModel.stopSensors()
       val sensorS=SensorService()
       sensorS.onDestroy()
       val locS=LocationService()
       locS.onDestroy()
//       activity?.let { LocalBroadcastManager.getInstance(it.applicationContext).sendBroadcast(sensorServiceIntent) }
   }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext().applicationContext).registerReceiver(
            broadcastReceiver1,  IntentFilter(KEY_LOCATION_AND_SENSORS_VALUE_CHANGED_ACTION)
        )
        LocalBroadcastManager.getInstance(requireContext().applicationContext).registerReceiver(
            broadcastReceiver,  IntentFilter(KEY_LOCATION_VALUE_CHANGED)
        )
//        startForegroundServiceForSensors(false)
    }

    override fun onPause() {
        super.onPause()
        startForegroundServiceForSensors(true)
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver1)
        super.onDestroy()
    }

    private fun saveRawSensorData(sensorsModel: SensorsModel) {
        sensorsDataviewModel.saveSensorDataToDB(sensorsModel)

        }
    private fun savePoint(trackPoint: TrackPoint){
        lifecycleScope.launch {
            sensorsDataviewModel.savePoint(trackPoint)
        }
    }
    private fun saveTrack(counter:Int){
        //                ExternalFactorsModel Data
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Random().ints(8, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
        } else {
            // Descriptive alphabet using three CharRange objects, concatenated
            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

            // Build list from 20 random samples from the alphabet,
            // and convert it to a string using "" as element separator
            List(20) { alphabet.random() }.joinToString("")
        }
        val track=sensorsDataviewModel.getComputedTrack(tripId)
        if (counter==1){
            StoreIDs.storeStartTrackIdLocally(requireActivity(),track.trackID,editor)
        }
        StoreIDs.storeTrackIDLocally(requireActivity(),track.trackID,editor)
        StoreIDs.storeStartDateLocally(requireActivity(),track.startDate,editor)

        lifecycleScope.launch {
            sensorsDataviewModel.saveTrack(track)
        }

    }

    fun updateTrack(endDate:String,
                            o_experience:String,
                            drunk_state:String,
                            overloading:String){
        //                ExternalFactorsModel Data
//        val trackId=UUID.randomUUID()
//        val startDate=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
//        } else {
//
//        }
        val externalFactorsModel=ExternalFactorsModel(
            StoredToken.getStoredID(sf,"saved_trackId"),
            StoredToken.getStoredID(sf,"saved_start_date"),
            endDate,
            o_experience,
            drunk_state,
            overloading
        )
        lifecycleScope.launch {
            sensorsDataviewModel.updateTrack(externalFactorsModel)
        }
    }

    private fun saveLastKnownPoints(lastKnownPoints: LastKnownPoints){
        lifecycleScope.launch {
            sensorsDataviewModel.saveLastKnownPoint(lastKnownPoints)
        }
    }




}