package com.example.payments.presentation.viewModels

import androidx.lifecycle.*
import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import com.example.payments.domain.usecases.GetPaymentsListUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PaymentsViewModel @Inject constructor(
    private val getPaymentsListUseCase: GetPaymentsListUseCase
) : ViewModel() {

    private val _finalPaymentsList = MutableLiveData<List<PaymentEntity>>()
    val finalPaymentsList: LiveData<List<PaymentEntity>>
        get() = _finalPaymentsList

    fun getPaymentsList(token: String): LiveData<List<PaymentEntity>> {
        viewModelScope.launch {
            _finalPaymentsList.value = mapValidPayments(getPaymentsListUseCase.invoke(token))
        }
        return _finalPaymentsList
    }

    private fun mapValidPayments(paymentsList: List<PaymentEntity>): List<PaymentEntity> {
        val validPayments = paymentsList.filter {
            it.title is String && it.amount is Number && it.created is Long && it.id is Int
        }
        return validPayments
    }
}




