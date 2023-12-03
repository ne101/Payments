package com.example.payments.data.api

import com.example.payments.data.model.loginModel.LoginData
import com.example.payments.data.model.loginModel.LoginResponse
import com.example.payments.data.model.paymentsModel.PaymentsResponse
import retrofit2.http.*

interface ApiService {
     @Headers("app-key: 12345", "v: 1")
     @POST("login")
     suspend fun login(@Body loginData: LoginData): LoginResponse

     @Headers("app-key: 12345", "v: 1")
     @GET("payments")
     suspend fun getPayments(@Header("token") token: String): PaymentsResponse
 }