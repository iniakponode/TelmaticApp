package com.uoa.telmaticsapp.presentation.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.databinding.FragmentExternalFactorsForm1Binding
import com.uoa.telmaticsapp.presentation.viewModel.SensorsDataViewModel
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.presentation.viewModel.ExternalFactorsViewModel
import dagger.hilt.android.AndroidEntryPoint
//import com.uoa.telmaticsapp.data.db.DDCAPDB
//import com.uoa.telmaticsapp.databinding.FragmentDrinkStateFormBinding
import kotlin.streams.asSequence

@AndroidEntryPoint
class ExternalFactorsForm1 : Fragment() {
    private val viewModel: SensorsDataViewModel by activityViewModels()
    private lateinit var sf:SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private  lateinit var binding: FragmentExternalFactorsForm1Binding
    private lateinit var bundle: Bundle
    private lateinit var intent: Intent
    private val externalFactorsViewModel: ExternalFactorsViewModel by activityViewModels()

       @RequiresApi(Build.VERSION_CODES.N)
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           sf = activity?.getSharedPreferences("saved_device_token", AppCompatActivity.MODE_PRIVATE)!!
           editor = sf.edit()
        // Inflate the layout for this fragment
           binding=FragmentExternalFactorsForm1Binding.inflate(inflater,container,false)
//           binding.tvFileStoredDeviceTokendrinkState.text="deviceToken"

           val dToken= StoredToken.getStoredID(sf,"saved_device_token")
//               StoredToken.getStoredDeviceToken(sf,getString(R.string.deviceToken))

           binding.tvFileStoredDeviceTokendrinkState.text="DeviceModel Token: "+dToken
           Log.i("DeviceModel-TokenSaved",dToken.toString())
//           val tripId=java.util.UUID.randomUUID().toString()

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
           binding.btnDrinkForm.setOnClickListener {

                       Log.i("RandomString",tripId)
               if(validateData()) {
                   val drinkStatus = selectedStatus(binding.radioGroup)
                   val overloading=selectedStatus(binding.radioGroup2)

                   saveSelectedDrinkStatus(drinkStatus,tripId)
                   saveSelectedLoadingStatus(overloading,tripId)
                   saveTripId(tripId)
                   exportDatabase()
//                   bundle= bundleOf("drink_status" to drinkStatus,"overloading" to overloading)
                   findNavController().navigate(R.id.action_drinkStateForm_to_numberOfBottles)
               }
               else{
                   Toast.makeText(activity,"Please Click Yes or No, to answer the question",Toast.LENGTH_LONG).show()
               }
//
           }

        return binding.root
    }

    private fun exportDatabase(){
        val sd = Environment.getExternalStorageDirectory()

        // Get the Room database storage path using SupportSQLiteOpenHelper
//        val db= DDCAPDB.getInstance(requireContext())!!.openHelper.writableDatabase.path

//        if (sd.canWrite()) {
//            val backupDBPath = "mydb.sqlite"      //you can modify the file type you need to export
//            val currentDB = FileIO(db)
//            val backupDB = FileIO(sd, backupDBPath)
//            if (currentDB.exists()) {
//                try {
//                    val src = FileIS(currentDB).channel
//                    val dst = FileOS(backupDB).channel
//                    dst.transferFrom(src, 0, src.size())
//                    src.close()
//                    dst.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//        }
    }

    fun getRandomString(length: Int) : String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return List(length) { charset.random() }
            .joinToString("")
    }

    private fun selectedStatus(radioGroup:RadioGroup):String {
        var drinkStatus = 0
        var overloading=0
        if (radioGroup == binding.radioGroup) {
            val checkedStatus = binding.radioGroup.checkedRadioButtonId
//        Log.i("SelectedRG1BSelected",checkedStatus.toString())
            if (checkedStatus.equals(binding.rBYes.id)) {
//            Log.i("SelectedYes",checkedStatus.equals(binding.rBYes.id).toString())
                drinkStatus = 1
            } else {
//            Log.i("SelectedNo",checkedStatus.equals(binding.rBYes.id).toString())
                drinkStatus = 0
            }
//        Log.i("SelectedDrinkStatus",drinkStatus.toString())
            return drinkStatus.toString()
        }
        else{
            val checkedStatus = binding.radioGroup2.checkedRadioButtonId
//            Log.i("SelectedRG2BSelected",checkedStatus.toString())

            if (checkedStatus.equals(binding.tBYes.id)) {
//            Log.i("SelectedOverloadingYes",checkedStatus.equals(binding.tBYes.id).toString())
                overloading = 1
            } else {
//            Log.i("SelectedOverloadingNo",checkedStatus.equals(binding.tBYes.id).toString())
                overloading = 0
            }
//        Log.i("SelectedOloadingStatus",overloading.toString())

            return overloading.toString()
        }

    }
// Save drinking state to viewModel
private fun saveSelectedDrinkStatus(drinkStatus:String,tripId:String){
    if (drinkStatus=="1"){
        Log.i("SelectedStatDrinking",drinkStatus.toString())
        externalFactorsViewModel.storeDrinkStateLabel(drinkStatus)
    }
    else{
        Log.i("SelectedStatDrinking",drinkStatus.toString())
        externalFactorsViewModel.storeDrinkStateLabel(drinkStatus)

    }
}
    // Save overloading status to viewModel
    private fun saveSelectedLoadingStatus(loading:String,tripId:String){
        if (loading=="1"){
            Log.i("SelectedStatOverloading",loading.toString())
            externalFactorsViewModel.saveOverloadingLabel(loading)
            val bundle= bundleOf("loading" to loading,"tripId" to tripId)
            // insertData(dToken,drinkStatus,tripId)
        }
        else{
            Log.i("SelectedOverloadingStat",loading)
            externalFactorsViewModel.saveOverloadingLabel(loading)

        }
    }

    private fun saveTripId(tId:String){
//        viewModel.saveTripId(tId)
    }

//    Check to ensure the drink and overloading statuses are all checked
    private fun validateData():Boolean{
        val checkedStatus=binding.radioGroup.checkedRadioButtonId
        return (binding.radioGroup.checkedRadioButtonId.equals(binding.rBYes.id)||
                binding.radioGroup.checkedRadioButtonId.equals(binding.rBNo.id)||
                binding.radioGroup.checkedRadioButtonId.equals(binding.tBYes.id)||
                binding.radioGroup.checkedRadioButtonId.equals(binding.tBNo.id))
    }
}