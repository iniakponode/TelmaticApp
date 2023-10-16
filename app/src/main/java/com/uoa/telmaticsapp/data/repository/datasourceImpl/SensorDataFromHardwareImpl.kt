package com.uoa.telmaticsapp.data.repository.datasourceImpl
import android.hardware.SensorManager
import android.os.Build
import com.uoa.telmaticsapp.data.model.SensorsModel
import com.uoa.telmaticsapp.data.repository.datasource.SensorDataFromHardware
import com.uoa.telmaticsapp.presentation.di.*
import com.uoa.telmaticsapp.presentation.ui.StoredToken
import com.uoa.telmaticsapp.util.TrackingSensor
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_INSTANT
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject
import kotlin.streams.asSequence

class SensorDataFromHardwareImpl @Inject constructor(
//    @LightSensorM private val lightSensor: LightSensor,
    @AccelerometerSensorM private val accelerometerSensorM: TrackingSensor,
//    @AccelerometerSensorLtdAM private  val accelerometerSensorLtdAM: TrackingSensor,
    @AccelerometerSensorLtdAUnCalibratedM private val accelerometerSensorLtdAUnCalibratedM: TrackingSensor,
    @GPSLocationSensorM private val gpsLocationSensorM: TrackingSensor,
    @GyroscopeSensorM private val gyroscopeSensorM: TrackingSensor,
//    @GyroscopeSensorLtdAM private val gyroscopeSensorLtdAM: TrackingSensor,
    @GyroscopeSensorLtdAUnCalibratedM private val gyroscopeSensorLtdAUnCalibratedM: TrackingSensor,
    @LinearAccelerationM private val linearAccelerationM: TrackingSensor,
//    @ProximitySensorM private val proximitySensorM: TrackingSensor,
    @RotationVectorSensorM private val rotationVectorM: TrackingSensor,
    @MagnetometerSensorM private val magnetometerSensorM: TrackingSensor,
    @GravitySensorM private val gravitySensorM:TrackingSensor
//    var sensorsData: SensorsModel

    ):SensorDataFromHardware{



    //    val sensorDataID=UUID.randomUUID().toString()
    var accelVal:List<Float> = listOf(0.0f,0.0f,0.0f)
//    var accelValltdA:List<Float> = listOf(0.0f,0.0f,0.0f)
    var accelValltdAUnc:List<Float> = listOf(0.0f,0.0f,0.0f)
    var accelAcc=0
    var gyroVal:List<Float> = listOf(0.0f,0.0f,0.0f)
//    var gyrltdAVal:List<Float> = listOf(0.0f,0.0f,0.0f)
    var gyroltAUnc:List<Float> = listOf(0.0f,0.0f,0.0f)
    var gyroAcc=0
    //    var GPSLoc:List<Float> = listOf(0.0f,0.0f,0.0f)
//    var proximity:List<Float> = listOf(0.0f,0.0f,0.0f)
//    var proximityAcc=0
    var linearAccele:List<Float> = listOf(0.0f,0.0f,0.0f)
    var linAcc=0
    var rotationVector: List<Float> = listOf(0.0f,0.0f,0.0f)
    var rotVectAcc=0
    var magnetometer: List<Float> = listOf(0.0f,0.0f,0.0f)
    var magnAcc=0
    var gravity: List<Float> = listOf(0.0f,0.0f,0.0f)
    var gravityAcc=0
    var yaw: Float = 0.0f
    var pitch: Float=0.0f
    var roll: Float=0.0f
    var sensorID=""
//    var speed: Float=0.0f



    //    var sensorsData=SensorsModel(listOf(0.0f),listOf(0.0f),listOf(0.0f),listOf(0.0f),listOf(0.0f),listOf(0.0f),
//        listOf(0.0f),listOf(0.0f),listOf(0.0f),"0:0:0:0:0")


//init {
//    getSensorDataFromHardwre(sensorDataID)
//}
    override fun getSensorDataFromHardwre(): Flow<List<SensorsModel>> {



    val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
    } else {
        // For Android versions below Oreo (API level 26), use SimpleDateFormat
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        date.timeZone = TimeZone.getTimeZone("UTC")
        date.format(Date())
    }
//        val rate=SensorManager.SENSOR_DELAY_FASTEST
        val rate=200
        gyroscopeSensorM.startlisteningToSensor(rate)
//        gyroscopeSensorLtdAM.startlisteningToSensor(rate)
        gyroscopeSensorLtdAUnCalibratedM.startlisteningToSensor(rate)
        accelerometerSensorM.startlisteningToSensor(rate)
//        accelerometerSensorLtdAM.startlisteningToSensor(rate)
        accelerometerSensorLtdAUnCalibratedM.startlisteningToSensor(rate)
        gpsLocationSensorM.startlisteningToSensor(rate)
        linearAccelerationM.startlisteningToSensor(rate)
//        proximitySensorM.startlisteningToSensor(rate)
        rotationVectorM.startlisteningToSensor(rate)
        magnetometerSensorM.startlisteningToSensor(rate)
        gravitySensorM.startlisteningToSensor(rate)

        var accurccyStat=false

        rotationVectorM.setOnSensorValuesChangedListener {
            val rotationMatrix= FloatArray(9)
//            Log.i("rotVect",it.size.toString())
            val orientation=FloatArray(3)
            SensorManager.getRotationMatrixFromVector(rotationMatrix,it.toFloatArray())
            SensorManager.getOrientation(rotationMatrix, orientation)
            this.yaw=orientation[0]
            this.pitch=orientation[1]
            this.roll=orientation[2]
            sensorID=StoredToken.tripId
        }

        accelerometerSensorM.setOnSensorValuesChangedListener {
            this.accelVal=it
            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                java.util.Random().ints(8, 0, source.length)
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
//            Log.i("Accele",it.size.toString())
            sensorID=tripId

            accelerometerSensorM.setOnAccuracyValueChangedListener(accurccyStat){
                this.accelAcc=it
                accurccyStat=true

            }
        }
//        accelerometerSensorLtdAM.setOnSensorValuesChangedListener {
//            this.accelValltdA=it
////            Log.i("acceleltda",it.size.toString())
//            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//            val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                java.util.Random().ints(8, 0, source.length)
//                    .asSequence()
//                    .map(source::get)
//                    .joinToString("")
//            } else {
//                // Descriptive alphabet using three CharRange objects, concatenated
//                val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//
//                // Build list from 20 random samples from the alphabet,
//                // and convert it to a string using "" as element separator
//                List(20) { alphabet.random() }.joinToString("")
//            }
//
//            sensorID=tripId
//
//        }

        accelerometerSensorLtdAUnCalibratedM.setOnSensorValuesChangedListener {
            this.accelValltdAUnc=it
//            Log.i("AcceleLTDUN",it.size.toString())
            sensorID=StoredToken.tripId
        }


        linearAccelerationM.setOnSensorValuesChangedListener {
            this.linearAccele=it
//            Log.i("linAcc",it.size.toString())
            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                java.util.Random().ints(8, 0, source.length)
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

            sensorID=tripId

            linearAccelerationM.setOnAccuracyValueChangedListener(accurccyStat){
                this.linAcc=it
                accurccyStat=true

            }
        }
//        proximitySensorM.setOnSensorValuesChangedListener {
//            this.proximity=it
////            Log.i("Proxim",it.size.toString()+it.forEach {
////                it.toString()
////            })
//
//            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//            val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                java.util.Random().ints(8, 0, source.length)
//                    .asSequence()
//                    .map(source::get)
//                    .joinToString("")
//            } else {
//                // Descriptive alphabet using three CharRange objects, concatenated
//                val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//
//                // Build list from 20 random samples from the alphabet,
//                // and convert it to a string using "" as element separator
//                List(20) { alphabet.random() }.joinToString("")
//            }
//
//            sensorID=tripId
//
//            this.proximitySensorM.setOnAccuracyValueChangedListener(accurccyStat){
//                this.proximityAcc=it
//                accurccyStat=true
//
//            }
//        }
        gyroscopeSensorM.setOnSensorValuesChangedListener {
            this.gyroVal=it
//            Log.i("Gyro",it.size.toString())

            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                java.util.Random().ints(8, 0, source.length)
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

            sensorID=tripId

            gyroscopeSensorM.setOnAccuracyValueChangedListener(accurccyStat){
                this.gyroAcc=it
                accurccyStat=true

            }
        }
//    gyroscopeSensorLtdAM.setOnSensorValuesChangedListener {
//        this.gyrltdAVal=it
////        Log.i("gyroLtda",it.size.toString())
//        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
//        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            java.util.Random().ints(8, 0, source.length)
//                .asSequence()
//                .map(source::get)
//                .joinToString("")
//        } else {
//            // Descriptive alphabet using three CharRange objects, concatenated
//            val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
//
//            // Build list from 20 random samples from the alphabet,
//            // and convert it to a string using "" as element separator
//            List(20) { alphabet.random() }.joinToString("")
//        }
//
//        sensorID=tripId
//    }
    gyroscopeSensorLtdAUnCalibratedM.setOnSensorValuesChangedListener {
        this.gyroltAUnc=it
//        Log.i("gyroltdaUn",it.size.toString())
    }

    gravitySensorM.setOnSensorValuesChangedListener {
        this.gravity=it
//        Log.i("gravity",it.size.toString())
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            java.util.Random().ints(8, 0, source.length)
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

        sensorID=tripId

        gravitySensorM.setOnAccuracyValueChangedListener(accurccyStat){
            this.gravityAcc=it
            accurccyStat=true

        }
    }

    magnetometerSensorM.setOnSensorValuesChangedListener {
//        Log.i("Magneto",it.size.toString())
        this.magnetometer=it

        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            java.util.Random().ints(8, 0, source.length)
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

        sensorID=tripId

        magnetometerSensorM.setOnAccuracyValueChangedListener(accurccyStat){
            this.magnAcc=it
                accurccyStat=true

            }
    }

    rotationVectorM.setOnSensorValuesChangedListener {
        this.rotationVector=it
//        Log.i("RotVect",it.size.toString())
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val tripId= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            java.util.Random().ints(8, 0, source.length)
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

        sensorID=tripId
        rotationVectorM.setOnAccuracyValueChangedListener(accurccyStat){
            this.rotVectAcc=it
            accurccyStat=true

        }
    }

        val sensorsModel=SensorsModel(
            sensorID,
            this.accelVal,
//            this.accelValltdA,
            this.accelValltdAUnc,
            this.accelAcc,
            this.linearAccele,
            this.linAcc,
            this.gyroVal,
//            this.gyrltdAVal,
            this.gyroltAUnc,
            this.gyroAcc,
            this.gravity,
            this.gravityAcc,
            this.magnetometer,
            this.magnAcc,
//            this.proximity,
//            this.proximityAcc,
            this.rotationVector,
            this.rotVectAcc,
            this.yaw,
            this.pitch,
            this.roll,
            date as String
        )
       return flow {
           this.emit(
               listOf(
                   sensorsModel
               )

           )
       }
//           return listOf(sensorsData)
       }

    override suspend fun stopSensorData() {
        gyroscopeSensorM.stopListeningToSensor()
//        gyroscopeSensorLtdAM.stopListeningToSensor()
        gyroscopeSensorLtdAUnCalibratedM.stopListeningToSensor()
        accelerometerSensorM.stopListeningToSensor()
//        accelerometerSensorLtdAM.stopListeningToSensor()
        accelerometerSensorLtdAUnCalibratedM.stopListeningToSensor()
        gpsLocationSensorM.stopListeningToSensor()
        linearAccelerationM.stopListeningToSensor()
//        proximitySensorM.stopListeningToSensor()
    }
    }