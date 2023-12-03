package com.example.payments.data.di

import androidx.lifecycle.ViewModel
import com.example.payments.presentation.viewModels.AuthorizationViewModel
import com.example.payments.presentation.viewModels.PaymentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindAuthorizationViewModel(viewModel: AuthorizationViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentsViewModel::class)
    fun bindPaymentsViewModel(viewModel: PaymentsViewModel):ViewModel

}