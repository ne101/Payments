package com.example.payments.data.di

import com.example.payments.presentation.fragments.AuthorizationFragment
import com.example.payments.presentation.fragments.PaymentsFragment
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: AuthorizationFragment)
    fun inject(fragment: PaymentsFragment)
}