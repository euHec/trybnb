package com.betrybe.trybnb.data.models

import android.widget.EditText

data class AuthenticationRequest(
    val username: String,
    val password: String
)