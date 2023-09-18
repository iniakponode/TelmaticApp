package com.uoa.telmaticsapp.presentation.ui

//import com.uoa.phdapp.databinding.FragmentNumberOfBottlesBinding

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.R

import com.uoa.telmaticsapp.data.model.ExternalFactorsModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.uoa.telmaticsapp.databinding.FragmentExternalFactorsForm2Binding
import com.uoa.telmaticsapp.presentation.viewModel.SensorsDataViewModel
import com.uoa.telmaticsapp.presentation.viewModel.ExternalFactorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExternalFactorsForm2 : Fragment() {
    private lateinit var sf: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private var drinkStatus: String?=null
    private var overloading: String?=null
    private var otherExp:String=""
    private  var tripId: String?=null
    private lateinit var dToken:String
    private val viewModel: SensorsDataViewModel by activityViewModels()
    private lateinit var binding: FragmentExternalFactorsForm2Binding
    private lateinit var tripsList: List<ExternalFactorsModel>
    private lateinit var entries: Array<String>
    private val sensorsDataviewModel: SensorsDataViewModel by activityViewModels()
    private val externalFactorsViewModel:ExternalFactorsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sf = activity?.getSharedPreferences("saved_device_token", AppCompatActivity.MODE_PRIVATE)!!

        dToken=StoredToken.getStoredID(sf,"saved_device_token")

        // Inflate the layout for this fragment
        binding =FragmentExternalFactorsForm2Binding.inflate(inflater,container,false)
        binding.tvDTok.text="DeviceModel Token: "+dToken


//        Log.i("DeviceModel-TokenSaved",dToken.toString())
        val stat="drinkStatus"
         externalFactorsViewModel.drinkState.observe(requireActivity()) { drinkState ->
             drinkStatus = drinkState
         }
        externalFactorsViewModel.ovLoading.observe(requireActivity()){ ovLoading->
            overloading=ovLoading
        }
//        externalFactorsViewModel.tripID.observe(requireActivity()){tripID->
//            tripId=tripID
//        }

        binding.btnNoBottles.setOnClickListener {
            if (validateData()){
                sensorsDataviewModel.setBtnText("Start Trip")
                  otherExp=binding.etNoBottles.text.toString()

//                  viewModel.saveOExperience(otherExp)
                Log.i("trackDate",StoredToken.getStoredID(sf,"saved_end_date"))
                Log.i("trackOth",otherExp)
                Log.i("trackDS",drinkStatus!!)
                Log.i("trackOV",overloading!!)
                updateTrack(StoredToken.getStoredID(sf,"saved_end_date"),otherExp,drinkStatus!!,overloading!!)
//                insertTripData(dToken, drinkStatus!!,overloading!!, otherExp)
//                generateFiletoInternalM(tripsList,tripId.toString(),requireContext())
//                writeCSV(viewModel.tripID.value!!)
                findNavController().navigate(R.id.action_numberOfBottles_to_appstHome)
            }
            else{
                Toast.makeText(activity,"Please Enter quantity Taken",Toast.LENGTH_LONG).show()
            }


        }
        return binding.root
    }

    fun updateTrack(endDate:String,
                    o_experience:String,
                    drunk_state:String,
                    overloading:String){
        //                ExternalFactorsModel Data
        val externalFactorsModel=ExternalFactorsModel(
            StoredToken.getStoredID(sf,"saved_trackId"),
            StoredToken.getStoredID(sf,"saved_start_date"),
            endDate,
            o_experience,
            drunk_state,
            overloading
        )
        GlobalScope.launch {
            sensorsDataviewModel.updateTrack(externalFactorsModel)
//            exportDBToCSV(
//                track.drunk_state,
//                track.overloading,
//                track.other_experiences
//
//            )

        }
        Toast.makeText(activity,"Trip Data Taken Successfully", Toast.LENGTH_LONG).show()
    }

//    private fun exportDBToCSV(drunkState:String, overloading: String, o_experience:String) {
//        val fileUtil= FileUtils()
//        val csvFile=fileUtil.generateFile(requireContext(),getCSVFileName())
//
//        if(csvFile!=null){
//
//            csvWriter().open(csvFile,append =false){
//                writeRow(listOf("id","ExternalFactorsModel ID","Start Date","End Date","Drunk State","Overloading","Other Experiences"))
//                    tripsList.forEachIndexed { index, track ->
//                            writeRow(
//                                listOf(
//                                    index.toString(),
//                                    StoredToken.getStoredID(sf,"saved_trackId"),
//                                    StoredToken.getStoredID(sf,"saved_start_date"),
//                                    StoredToken.getStoredID(sf,"saved_end_date"),
//                                    overloading,
//                                    drunkState,
//                                    o_experience
//                                )
//                            )
//
//
//                    }
//
//
//                Log.i("CSV",csvFile!!.path.toString())
//            }
//
//            Toast.makeText(requireContext(), getString(string.csv_file_generated_text), Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(requireContext(), getString(string.csv_file_not_generated_text), Toast.LENGTH_LONG).show()
//        }
//    }
    private fun getCSVFileName(): String="TripFollowUpQ"+tripId+".csv"


    private fun validateData():Boolean{

        return !(TextUtils.isEmpty(binding.etNoBottles.text))
    }

//    companion object {
//        fun newInstance(): ExternalFactorsForm2 = ExternalFactorsForm2()
//    }

}