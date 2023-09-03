package com.uoa.telmaticsapp.data.db


import android.graphics.Point
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uoa.telmaticsapp.data.db.DataAccessObjects.*
import com.uoa.telmaticsapp.data.model.*

@Database(entities = [
    User::class,
    Track::class,
    TripDetails::class,
    TrackPoint::class,
    LoginAPIResponse::class,
    LastKnownPoints::class,
    LawEstablishingRule::class,
    UserAPIResponse::class,
    SensorsData::class], version=14, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PhDSafeDriveDB: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun userAPIResponse(): UserAPIResponseDAO
    abstract fun trackDAO(): TrackDAO
    abstract fun tripDetailsDAO(): TripDetailsDAO
    abstract fun pointDAO():PointDAO
    abstract fun sensorDataDAO():SensorDataDAO
    abstract fun lastKnownPointsDAO():LastKnownPointsDAO
    abstract fun lawEstablishingRuleDAO():LawEstablishingRuleDAO
    abstract fun loginAPIResponseDAO(): LoginAPIResponseDAO

}