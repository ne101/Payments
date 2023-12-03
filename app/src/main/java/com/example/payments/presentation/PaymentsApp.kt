package com.example.payments.presentation

import android.app.Application
import com.example.payments.data.di.DaggerApplicationComponent


class PaymentsApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.create()
    }
}