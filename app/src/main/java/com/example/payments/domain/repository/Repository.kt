package com.example.payments.domain.repository

import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.domain.entities.userEntities.UserTokenEntity

interface Repository {

    suspend fun login(userEntity: UserEntity): UserTokenEntity
    suspend fun getPaymentsList(token: String): List<PaymentEntity>

}