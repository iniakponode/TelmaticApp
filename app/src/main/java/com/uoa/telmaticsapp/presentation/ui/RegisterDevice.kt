package com.uoa.telmaticsapp.presentation.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.databinding.FragmentRegisterDeviceBinding
import com.uoa.telmaticsapp.data.model.User
import com.uoa.telmaticsapp.presentation.viewModel.UserDataViewModel
import com.uoa.telmaticsapp.util.StoreIDs
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class RegisterDevice : Fragment() {
private lateinit var binding: FragmentRegisterDeviceBinding


private lateinit var sf: SharedPreferences
private lateinit var editor : SharedPreferences.Editor
private val userViewModel: UserDataViewModel by activityViewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sf = activity?.getSharedPreferences("saved_device_token", AppCompatActivity.MODE_PRIVATE)!!
        editor = sf.edit()
        binding=FragmentRegisterDeviceBinding.inflate(layoutInflater,container,false)
        createUserToken()

        return binding.root
    }

    fun createUserToken() {
        val deviceToken = UUID.randomUUID()
//        val bundle = bundleOf("deviceToken" to deviceToken)
        StoreIDs.storeDeviceTokenLocally(requireContext(), deviceToken.toString(), editor)
        val user = User(
            deviceToken.toString(),
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            ""
        )

        if (userViewModel.InitializeUser(user)) {
                StoreIDs.storeDeviceTokenLocally(requireContext(), deviceToken.toString(), editor)
//                .storeTokenLocally(deviceToken.toString(),editor)
                Toast.makeText(activity, "Token Created and added Successfully", Toast.LENGTH_LONG)
                    .show()
                findNavController().navigate(
                    R.id.action_registerDevice_to_permissionsFragment
                )
            } else {
                Toast.makeText(activity, "Sorry, Token could not be created", Toast.LENGTH_LONG)
                    .show()
                userViewModel.updateSBText("Register User")
                findNavController().navigate(R.id.action_registerDevice_to_home)
            }
    }

}