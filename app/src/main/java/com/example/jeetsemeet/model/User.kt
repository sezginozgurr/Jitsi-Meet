package com.example.jeetsemeet.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(val firstName: String, val lastName: String, val email: String, val token: String) : Serializable
