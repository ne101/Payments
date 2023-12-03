package com.example.payments.data.mapper

import com.example.payments.data.model.loginModel.LoginData
import com.example.payments.data.model.loginModel.LoginResponse
import com.example.payments.data.model.paymentsModel.Payment
import com.example.payments.data.model.paymentsModel.PaymentsResponse
import com.example.payments.domain.entities.paymentEntities.PaymentEntity
import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.domain.entities.userEntities.UserTokenEntity
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapUserToLoginData(userEntity: UserEntity): LoginData = LoginData(
        login = userEntity.login,
        password = userEntity.password
    )

    fun mapLoginResponseToUserSession(loginResponse: LoginResponse): UserTokenEntity =
        UserTokenEntity(
            token = loginResponse.tokenResponse.token
        )

    private fun mapPaymentToPaymentEntity(payment: Payment): PaymentEntity = PaymentEntity(
        amount = payment.amount.toString().toBigIntegerOrNull() ?: payment.amount.toString()
            .toBigDecimalOrNull(),
        created = payment.created.toString().toLongOrNull(),
        id = payment.id.toString().toIntOrNull(),
        title = payment.title.toString()
    )

    fun mapPaymentsResponseToPaymentsListEntity(list: List<Payment>) =
        list.map { mapPaymentToPaymentEntity(it) }
}