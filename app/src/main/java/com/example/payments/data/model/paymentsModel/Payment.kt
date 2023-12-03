package com.example.payments.data.model.paymentsModel

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?
)