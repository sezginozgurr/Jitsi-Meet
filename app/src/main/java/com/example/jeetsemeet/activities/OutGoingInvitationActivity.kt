package com.example.jeetsemeet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jeetsemeet.R
import com.example.jeetsemeet.databinding.ActivityOutGoingInvitationBinding
import com.example.jeetsemeet.model.User

class OutGoingInvitationActivity : AppCompatActivity() {

    lateinit var binding: ActivityOutGoingInvitationBinding
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutGoingInvitationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val meetingType: String? = intent.getStringExtra("type")
        if (meetingType != null) {
            if (meetingType == "video") {
                binding.imageMeetingType.setImageResource(R.drawable.ic_round_videocam_24)
            } else {
                binding.imageMeetingType.setImageResource(R.drawable.ic_round_call_24)
            }
        }
        user = intent.getStringExtra("user") as User
    }
}