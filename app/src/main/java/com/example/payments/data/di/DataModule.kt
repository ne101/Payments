package com.example.payments.data.di

import com.example.payments.data.api.ApiFactory
import com.example.payments.data.api.ApiService
import com.example.payments.data.repositoryImpl.RepositoryImpl
import com.example.payments.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepositoryImpl(impl: RepositoryImpl): Repository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService
    }
}