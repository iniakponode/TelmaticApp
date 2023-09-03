package com.uoa.telmaticsapp.presentation.ui.util

import android.Manifest.permission.*

sealed class Permission(vararg val permissions: String) {
//     Individual permissions
//    object Camera : Permission(CAMERA)
//    object FineLoction: Permission(ACCESS_FINE_LOCATION)
//    object CoarsLocation: Permission(ACCESS_COARSE_LOCATION)
    object Forground: Permission(FOREGROUND_SERVICE)
    object ActivityReg: Permission(ACTIVITY_RECOGNITION)
    object Battery: Permission(REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
    object BackgroundAccLoc: Permission(ACCESS_BACKGROUND_LOCATION)


//     Bundled permissions
    object MandatoryForFeatureOne : Permission(ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION)

    // Grouped permissions
    object Location : Permission(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
//    object Storage : Permission(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)


    companion object {
        fun from(permission: String) = when (permission) {
            ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION -> Location
//            WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE -> Storage
            FOREGROUND_SERVICE->Forground
            REQUEST_IGNORE_BATTERY_OPTIMIZATIONS->Battery
            ACTIVITY_RECOGNITION->ActivityReg
//            CAMERA -> Camera
            else -> throw IllegalArgumentException("Unknown permission: $permission")
        }
    }
}