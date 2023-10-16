package com.uoa.telmaticsapp.presentation.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.databinding.FragmentHomeBinding
import com.uoa.telmaticsapp.presentation.viewModel.DeviceDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {
    private val userViewModel by viewModels<DeviceDataViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var regStatBtn: String
    private lateinit var sf: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container,false)
        binding.userViewModel=userViewModel
//        userViewModel.btnText.observe(requireActivity()){
//
//        }
        userViewModel.btnText.observe(requireActivity()){ btnText->
            regStatBtn=btnText
        }
//
            binding.btncreateToken.setOnClickListener {
                userViewModel.updateSBText("Update DeviceModel")
                it.findNavController().navigate(R.id.action_home_to_registerDevice)
        }
        return binding.root
    }

}