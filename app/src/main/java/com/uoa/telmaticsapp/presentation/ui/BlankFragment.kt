package com.uoa.telmaticsapp.presentation.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.databinding.FragmentBlankBinding

// TODO: Rename parameter arguments, choose names that match


class BlankFragment : Fragment() {
    private lateinit var binding: FragmentBlankBinding
    private lateinit var sf: SharedPreferences
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBlankBinding.inflate(inflater,container,false)
        sf = activity?.getSharedPreferences("saved_device_token", AppCompatActivity.MODE_PRIVATE)!!
        val devToken=StoredToken.getStoredID(sf,"saved_device_token")
        Log.i("Home_Device_Token",devToken)
        if (devToken=="null")
            findNavController().navigate(R.id.action_blank_to_home)
        else
            findNavController().navigate(R.id.action_blank_to_appstHome)
        return binding.root
    }


}