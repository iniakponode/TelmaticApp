package com.uoa.telmaticsapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
//import androidx.hilt.navigation.compose.hiltViewModel
import com.uoa.telmaticsapp.R
import com.uoa.telmaticsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel= hiltViewModel<LightSensorViewModel>()
        viewBinding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
    }

}