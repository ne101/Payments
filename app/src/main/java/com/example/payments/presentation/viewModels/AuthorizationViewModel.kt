package com.example.payments.presentation.viewModels

import android.graphics.Color
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.domain.entities.userEntities.UserTokenEntity
import com.example.payments.domain.usecases.GetPaymentsListUseCase
import com.example.payments.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResult = MutableLiveData<UserTokenEntity>()
    val loginResult: LiveData<UserTokenEntity>
        get() = _loginResult

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty


    fun login(userEntity: UserEntity) {
        viewModelScope.launch {
            val result = loginUseCase(userEntity)
            _loginResult.value = result
        }
    }

    fun checkValid(etLogin: EditText, etPassword: EditText, userEntity: UserEntity): Boolean {
        val login = etLogin.text.trim().toString()
        val password = etPassword.text.trim().toString()

        var isValid = true

        if (login.isEmpty() || password.isEmpty()) {
            etLogin.setBackgroundColor(Color.parseColor("#B3FF0000"))
            etPassword.setBackgroundColor(Color.parseColor("#B3FF0000"))
            isValid = false
            _isEmpty.value = true
        } else {
            _isEmpty.value = false
        }

        if (login != userEntity.login && login.isNotEmpty() ||
            password != userEntity.password && password.isNotEmpty()) {
            etLogin.setBackgroundColor(Color.parseColor("#B3FF0000"))
            etPassword.setBackgroundColor(Color.parseColor("#B3FF0000"))
            isValid = false
            _isError.value = true
        } else {
            _isError.value = false
        }

        return isValid
    }
}