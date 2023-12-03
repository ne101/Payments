package com.example.payments.data.model.paymentsModel

import com.google.gson.annotations.SerializedName

data class PaymentsResponse(
    @SerializedName("response")
    val paymentResponse: List<Payment>
)