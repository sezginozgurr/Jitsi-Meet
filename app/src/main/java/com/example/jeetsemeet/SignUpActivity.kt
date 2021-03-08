package com.example.jeetsemeet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jeetsemeet.databinding.ActivityMainBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}