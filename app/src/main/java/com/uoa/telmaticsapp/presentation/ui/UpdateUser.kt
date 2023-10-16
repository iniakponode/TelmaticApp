package com.uoa.telmaticsapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.data.model.DeviceModel
import com.uoa.telmaticsapp.databinding.FragmentUpdateUserBinding
import com.uoa.telmaticsapp.presentation.viewModel.DeviceDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UpdateUser : Fragment() {
    private lateinit var binding: FragmentUpdateUserBinding
    private val userViewModel : DeviceDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_update_user,container,false)
        return binding.root
    }

    fun updateUser(
        deviceToken: UUID,
        firstName:String,
        lastname:String,
        nickname:String,
        phone:String,
        email:String,
        address:String,
        birthday:String,
        gender:String,
        maritalStatus:String,
        childrenCount:Int,
        userType:String

    ){
        val  bundle= bundleOf("deviceToken" to deviceToken.toString())
        val deviceModel= DeviceModel(
            deviceToken.toString(),
            firstName,
            lastname,
            nickname,
            phone,
            email,
            address,
            birthday,
            gender,
            maritalStatus,
            childrenCount,
            userType
        )
        if(userViewModel.updateUser(deviceModel)){
            Toast.makeText(activity,"DeviceModel data Successfully!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updUser_to_appstHome,bundle)
        }
        else{
            Toast.makeText(activity,"Sorry, DeviceModel Data Update Failed", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updUser_self,bundle)
        }

    }
}