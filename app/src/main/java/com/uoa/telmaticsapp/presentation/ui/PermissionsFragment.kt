package com.uoa.telmaticsapp.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.raxeltelematics.v2.sdk.Settings
import com.raxeltelematics.v2.sdk.TrackingApi
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsWizardActivity
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsWizardActivity.Companion.WIZARD_PERMISSIONS_CODE
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsWizardActivity.Companion.WIZARD_RESULT_ALL_GRANTED
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsWizardActivity.Companion.WIZARD_RESULT_CANCELED
import com.raxeltelematics.v2.sdk.utils.permissions.PermissionsWizardActivity.Companion.WIZARD_RESULT_NOT_ALL_GRANTED
import com.telematicssdk.auth.TelematicsAuth
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.databinding.FragmentPermissionsBinding
import com.uoa.telmaticsapp.presentation.ui.util.Permission
import com.uoa.telmaticsapp.presentation.ui.util.PermissionManager
import com.uoa.telmaticsapp.util.StoreIDs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionsFragment : Fragment() {
    /**
     * Default Setting constructor
     * Stop tracking time is 5 minute.
     * Parking radius is 100 meters.
     * Auto start tracking is true.
     * hfOn - true if HIGH FREQUENCY data recording from sensors (acc, gyro) is ON and false otherwise.
     * isElmOn - true if data recording from ELM327 devices is ON and false otherwise.
     */
    private lateinit var tracking: TrackingApi
    private lateinit var binding: FragmentPermissionsBinding
    private lateinit var sf: SharedPreferences
    private lateinit var bun:Bundle
    val Private_Drivers_INSTANCE_ID="abe10ef5-0cfb-4e3a-9702-859a96ec5abc"
    val Private_Drivers_INSTANCE_Key="839f0179-79b8-48c8-9a75-e55ba7b64aaf"

    val Public_Drivers_INSTANCE_ID="f4045fcf-43ce-4343-b36e-a01000d15019"
    val Public_Drivers_INSTANCE_Key="c35e5ee4-3d11-4a3a-be09-ad7393362bdb"
    private val permissionManager = PermissionManager.from(this)
    private val INSTANCE_ID=Private_Drivers_INSTANCE_ID
    private val INSTANCE_KEY=Private_Drivers_INSTANCE_Key
    private lateinit var editor : SharedPreferences.Editor
    val PERMISSION_ID = 42
//    val settings = Settings(Settings.stopTrackingTimeHigh, Settings.accuracyHigh, true, true, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sf = activity?.getSharedPreferences(getString(R.string.deviceToken), AppCompatActivity.MODE_PRIVATE)!!
        editor = sf.edit()
        // Inflate the layout for this fragment
        sf = activity?.getSharedPreferences(
            getString(R.string.deviceToken),
            AppCompatActivity.MODE_PRIVATE
        )!!
        val deviceToken = StoredToken.getStoredID(sf, "saved_tel_device_token")

        binding=FragmentPermissionsBinding.inflate(inflater,container,false)
        bun= bundleOf("deviceToken" to deviceToken)
        Log.i("DeviceToken-Permissions",deviceToken.toString())

//        permissionManager
            // Check a few bundled permissions under one: Storage = Read + Write
//            .request(Permission.MandatoryForFeatureOne)
//            .rationale("We require to demonstrate that we can request two permissions at once")
//            .checkPermission { granted ->
//                if (granted) {
//                    success("Permissions granted")
//                    Toast.makeText(requireContext(), "Permissions granted", Toast.LENGTH_LONG)
//                    findNavController().navigate(R.id.action_permissionsFragment_to_appstHome)
//                } else {
//                    Toast.makeText(requireContext(), "Still missing Location permission", Toast.LENGTH_LONG)
//                    findNavController().navigate(R.id.ac)
//                    error("Still missing at least one permission :(")
//                }
//            }


        permissionManager
            // Check all permissions without bundling them
            .request(Permission.Location,Permission.Forground, Permission.Battery, Permission.ActivityReg)
            .rationale("We want permission to Ignore battery Optimization, Location, Activity recognition and Foreground Services")
            .checkDetailedPermission { result ->
                if (result.all { it.value }) {
                    success("YES! Now I have full access :D")
                    findNavController().navigate(R.id.action_permissionsFragment_to_appstHome)
                } else {
                    showErrorDialog(result)
                }
            }



        return binding.root

    }


    private fun showErrorDialog(result: Map<Permission, Boolean>) {
        val message = result.entries.fold("") { message, entry ->
            message + "${getErrorMessageFor(entry.key)}: ${entry.value}\n"
        }
        Log.i("TAG", message)
        AlertDialog.Builder(requireContext())
            .setTitle("Missing permissions")
            .setMessage(message)
            .show()
    }

    private fun getErrorMessageFor(permission: Permission) = when (permission) {
//        Permission.Camera -> "Camera permission: "
        Permission.Location -> "Location permission"
        Permission.ActivityReg -> "Recognize Activity permission"
        Permission.Forground->"Forground Permission"
        Permission.Battery->"Batter Permission"
        else -> "Unknown permission"
    }

    private fun success(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .withColor(Color.hashCode())
            .show()
    }

    private fun error(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .withColor(Color.hashCode())
            .show()
    }

    private fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
        this.view.setBackgroundColor(colorInt)
        return this
    }



    }
