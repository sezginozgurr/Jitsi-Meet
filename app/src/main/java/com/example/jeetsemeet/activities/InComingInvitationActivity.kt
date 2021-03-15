package com.example.jeetsemeet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jeetsemeet.databinding.ActivityInComingInvitationBinding

class InComingInvitationActivity : AppCompatActivity() {

    lateinit var binding: ActivityInComingInvitationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInComingInvitationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}