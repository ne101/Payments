package com.example.payments.data.model.loginModel

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response")
    val tokenResponse: Token
)