package com.uoa.telmaticsapp.data.util

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Location
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.uoa.telmaticsapp.data.services.SensorService
import com.uoa.telmaticsapp.databinding.FragmentAppStartHomeBinding

import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import com.uoa.telmaticsapp.presentation.ui.AppStartHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class LocationMethods {
    companion object{
        var currentBestLocation: Location? = null
        val ONESECOND = 1000
        var addressArray= listOf("","","","","","")

        /**
         * This method modify the last know good location according to the arguments.
         *
         * @param location The possible new location.
         */
        fun makeUseOfNewLocation(location: Location) {

            if (isBetterLocation(location, currentBestLocation)) {
                currentBestLocation = location
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }

        /** Determines whether one location reading is better than the current location fix
         * @param location  The new location that you want to evaluate
         * @param currentBestLocation  The current location fix, to which you want to compare the new one.
         */
        protected fun isBetterLocation(location: Location, currentBestLocation: Location?): Boolean {
            if (currentBestLocation == null) {
                // A new location is always better than no location
                return true
            }

            // Check whether the new location fix is newer or older
            val timeDelta = location.time - currentBestLocation.time
            val isSignificantlyNewer: Boolean = timeDelta > ONESECOND
            val isSignificantlyOlder: Boolean = timeDelta < -ONESECOND
            val isNewer = timeDelta > 0

            // If it's been more than two minutes since the current location, use the new location,
            // because the user has likely moved.
            if (isSignificantlyNewer) {
                return true
                // If the new location is more than One Second older, it must be worse.
            } else if (isSignificantlyOlder) {
                return false
            }

            // Check whether the new location fix is more or less accurate
            val accuracyDelta = (location.accuracy - currentBestLocation.accuracy).toInt()
            val isLessAccurate = accuracyDelta > 0
            val isMoreAccurate = accuracyDelta < 0
            val isSignificantlyLessAccurate = accuracyDelta > 200

            // Check if the old and new location are from the same provider
            val isFromSameProvider = isSameProvider(
                location.provider,
                currentBestLocation.provider
            )

            // Determine location quality using a combination of timeliness and accuracy
            if (isMoreAccurate) {
                return true
            } else if (isNewer && !isLessAccurate) {
                return true
            } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
                return true
            }
            return false
        }

        // Checks whether two providers are the same
        private fun isSameProvider(provider1: String?, provider2: String?): Boolean {
            return if (provider1 == null) {
                provider2 == null
            } else provider1 == provider2
        }

        fun getAddress(context: Context, lat:Double, long:Double ): List<String> {

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses: List<Address>?
                    val address: Address?
                    var fulladdress = ""
//                    Log.i("Lat", lat.toString())
//                    Log.i("Long", long.toString())

                    addresses = geocoder.getFromLocation(lat, long, 1)

                    if (addresses != null) {

//
                        if (addresses.isNotEmpty()) {
                            address = addresses[0]
//                            Log.i("Address",addresses[0].getAddressLine(0)
                            fulladdress =
                                address.getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex
                            var city = address.getLocality();
                            var state = address.getAdminArea();
                            var country = address.getCountryName();
                            var postalCode = address.getPostalCode();
                            var knownName =
                                address.getFeatureName(); // Only if available else return NULL
                            addressArray= listOf(city,fulladdress,state,country,postalCode,knownName)

                        } else {
                            addressArray = listOf("not found at the moment")

                        }
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }

            }
            return addressArray
        }
        }
    }