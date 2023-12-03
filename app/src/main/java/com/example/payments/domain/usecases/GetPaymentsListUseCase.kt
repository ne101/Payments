package com.example.payments.domain.usecases

import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import com.example.payments.domain.repository.Repository
import javax.inject.Inject

class GetPaymentsListUseCase @Inject constructor(private val repository: Repository) {
   suspend operator fun invoke(token: String): List<PaymentEntity> {
        return repository.getPaymentsList(token)
    }
}