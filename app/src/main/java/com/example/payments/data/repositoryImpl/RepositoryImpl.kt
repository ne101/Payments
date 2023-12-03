package com.example.payments.data.repositoryImpl

import com.example.payments.data.api.ApiService
import com.example.payments.data.mapper.Mapper
import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.domain.entities.userEntities.UserTokenEntity
import com.example.payments.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mapper: Mapper,
    private val apiService: ApiService
): Repository {
    override suspend fun login(userEntity: UserEntity): UserTokenEntity {
        val loginData = mapper.mapUserToLoginData(userEntity)
        val loginResponse = apiService.login(loginData)
        return mapper.mapLoginResponseToUserSession(loginResponse)
    }

    override suspend fun getPaymentsList(token: String): List<PaymentEntity> {
        val response = apiService.getPayments(token)
        return mapper.mapPaymentsResponseToPaymentsListEntity(response.paymentResponse)
    }
}