package com.example.payments.domain.usecases

import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.domain.entities.userEntities.UserTokenEntity
import com.example.payments.domain.repository.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(userEntity: UserEntity): UserTokenEntity {
        return repository.login(userEntity)
    }
}