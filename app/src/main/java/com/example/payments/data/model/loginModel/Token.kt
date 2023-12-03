package com.example.payments.data.model.loginModel

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    val token: String
)