package com.uoa.telmaticsapp.data.repository.datasourceImpl

import android.os.Build
import android.util.Log
import com.uoa.telmaticsapp.data.model.TrackPoint
import com.uoa.telmaticsapp.data.model.SensorsData
import com.uoa.telmaticsapp.data.repository.datasource.ComputedPointFrmPhoneSensors
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
//import kotlin.math.PI
//import kotlin.math.atan
//import kotlin.math.atan2
import kotlin.math.sqrt
import kotlin.streams.asSequence

class ComputedPointFrmPhoneSensorsImpl(): ComputedPointFrmPhoneSensors {
    var pointID=0
    override fun computePointsFromHardware(sensorData:SensorsData,totalMets:Double,deceleration:Double, trackId:String, speed:Double,midSpeed:Double, long:String, lat:String): TrackPoint {
        val gravity=FloatArray(3)

        pointID+=1

//        Acceleration Filter
        val alpha = 0.8f

        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * sensorData.gravity[0] + (1 - alpha) * sensorData.accelerom[0]
        gravity[1] = alpha * sensorData.gravity[1] + (1 - alpha) * sensorData.accelerom[1]
        gravity[2] = alpha * sensorData.gravity[2] + (1 - alpha) * sensorData.accelerom[2]
        // Remove the gravity contribution with the high-pass filter.
        val accelerationX= sensorData.accelerom[0] - gravity[0]
        val accelerationY = sensorData.accelerom[1] - gravity[1]
        val accelerationZ = sensorData.accelerom[2] - gravity[2]
//        val accelLtdOriginalX=sensorData.acceleromltdA[0]
//        val accelLtdOriginalY=sensorData.acceleromltdA[1]
//        val accelLtdOriginalZ=sensorData.acceleromltdA[2]
        sensorData.acceleromltdAUncalibrated[0]
        sensorData.acceleromltdAUncalibrated[1]
        sensorData.acceleromltdAUncalibrated[2]
        val linearAccelerationX=sensorData.linearAcceleration[0]
        val linearAccelerationY=sensorData.linearAcceleration[1]
        val linearAccelerationZ=sensorData.linearAcceleration[2]
        val acceleration=computeAcceleration(accelerationX,accelerationY,accelerationZ)
        val accelerationXOriginal=sensorData.accelerom[0]
        val accelerationYOriginal=sensorData.accelerom[1]
        val accelerationZOriginal=sensorData.accelerom[2]
//        val accuracy: Double
//        val course: Double
//        val date=System.currentTimeMillis()
        val pointDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()
        } else {
            // Use SimpleDateFormat for Android versions lower than Oreo
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC") // You can adjust the timezone as needed
            dateFormat.format(Date())
        }

        val gyroscopeXOriginal=sensorData.gyroscope[0]
        val gyroscopeYOriginal=sensorData.gyroscope[1]
        val gyroscopeZOriginal=sensorData.gyroscope[2]

        val accelAcc=sensorData.accelerAcc
        val gyroAcc=sensorData.gyroscopeAcc
        val gravityAcc=sensorData.gravityAcc
        val linAcc=sensorData.linAccelerAcc
        val magnAcc=sensorData.magnetometerAcc
//        val proxAcc=sensorData.proximityAcc
        val rotVectAcc=sensorData.rotVectAcc

//        val gyroLtdOriginalX=sensorData.gyroscopeLtdA[0]
//        val gyroLtdOriginalY=sensorData.gyroscopeLtdA[1]
//        val gyroLtdOriginalZ=sensorData.gyroscopeLtdA[2]

//        val gyroLtdUnOriginalX=sensorData.gyroscopeLtdAUncalibrated[0]
//        val gyroLtdUnOriginalY=sensorData.gyroscopeLtdAUncalibrated[1]
//        val gyroLtdUnOriginalZ=sensorData.gyroscopeLtdAUncalibrated[2]

        val sensorgravityDataX=sensorData.gravity[0]
        val sensorgravityDataY=sensorData.gravity[1]
        val sensorgravityDataZ=sensorData.gravity[2]

        Log.i("magnetoSize",sensorData.magnetometer.size.toString())
        val magnetometerX=sensorData.magnetometer[0]
        val magnetometerY=sensorData.magnetometer[1]
//        val magnetometerZ=sensorData.magnetometer[2]

//        val proximityX=sensorData.proximity[0]
//        val proximityY=sensorData.proximity[1]
//        val proximityZ=sensorData.proximity[2]

        val rotVectorX=sensorData.RotVect[0]
        val rotVectorY=sensorData.RotVect[1]
        val rotVectorZ=sensorData.RotVect[2]



        val latitude=lat
        val longitude=long
//        val tickTimestamp=System.currentTimeMillis()
        val totalMeters=totalMets
//        val yaw=updateYaw(
//            accelerationX,
//            accelerationY,
//            accelerationZ
//        )
//        val pitch=updatePitch(
//            accelerationX,
//            accelerationY,
//            accelerationZ
//        )
//        val roll=updateRoll(
//            accelerationX,
//            accelerationY,
//            accelerationZ
//        )
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
        val yawFrmRotVector=sensorData.yaw
        val pitchFrmRotVector=sensorData.pitch
        val rollFrmRotVector=sensorData.roll

        val trackPointObject=TrackPoint(
            tripId,
        trackId,
        pointID,
        acceleration,
        accelerationXOriginal,
        accelerationYOriginal,
        accelerationZOriginal,
        accelAcc,
        deceleration,
        System.currentTimeMillis().toString(),
        gyroscopeXOriginal,
        gyroscopeYOriginal,
        gyroscopeZOriginal,
        gyroAcc,
        sensorgravityDataX,
        sensorgravityDataY,
        sensorgravityDataZ,
        gravityAcc,
        linearAccelerationX,
        linearAccelerationY,
        linearAccelerationZ,
        linAcc,
        magnetometerX,
        magnetometerY,
//        magnetometerZ,
        magnAcc,
//        proximityX,
//        proximityY,
//        proximityZ,
//        proxAcc,
        rotVectorX,
        rotVectorY,
        rotVectorZ,
        rotVectAcc,
        latitude,
        longitude,
        midSpeed,
            pointDate.toString(),
            speed,
            System.currentTimeMillis().toInt(),
            totalMeters,
//            yaw.toFloat(),
//            pitch.toFloat(),
//            roll.toFloat(),
            yawFrmRotVector.toDouble(),
            pitchFrmRotVector.toDouble(),
            rollFrmRotVector.toDouble())

        return trackPointObject

    }
//    private fun updateYaw(accelX: Float, accelY:Float, accelZ:Float):Double{
//        val  yaw = 180 * atan (accelZ/ sqrt(accelX*accelX + accelZ*accelZ)) / PI
//        return yaw
//    }
//    private fun updateRoll(accelX: Float, accelY:Float, accelZ:Float): Double{
//        val roll = 180 * atan2(accelY, sqrt(accelX*accelX + accelZ*accelZ)) / PI
//        return roll
//    }
//    private fun updatePitch(accelX: Float, accelY:Float, accelZ:Float):Double{
//        val pitch=180 * atan2(accelX, sqrt(accelY*accelY + accelZ*accelZ)) / PI
//        return pitch
//    }
    private fun computeAcceleration(accelx:Float, accely:Float, accelz:Float): Float {
        return sqrt(accelx*accelx+accely*accely+accelz*accelz)
    }
}