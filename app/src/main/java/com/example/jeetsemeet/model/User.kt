package com.example.jeetsemeet.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var token: String? = null
) : Serializable
