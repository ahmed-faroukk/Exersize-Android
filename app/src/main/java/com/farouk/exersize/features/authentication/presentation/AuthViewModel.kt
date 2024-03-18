package com.farouk.exersize.features.authentication.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farouk.exersize.features.authentication.data.Model.codeVerfication.VerifyCodeModel
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginModel
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupModel
import com.farouk.exersize.features.authentication.domain.usecase.AuthUseCase
import com.farouk.exersize.features.authentication.presentation.CodeVerfication.ReSendCodeState
import com.farouk.exersize.features.authentication.presentation.CodeVerfication.VerifyCodeState
import com.farouk.exersize.features.authentication.presentation.login.LoginState
import com.farouk.exersize.features.authentication.presentation.signup.SignUpState
import com.farouk.exersize.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState = _loginState

    private val _signUpState = mutableStateOf(SignUpState())
    val signUpState = _signUpState

    private val _codeVerifyState = mutableStateOf(VerifyCodeState())
    val codeVerifyState = _codeVerifyState

    private val _resendCodeState = mutableStateOf(ReSendCodeState())
    val resendCodeState = _resendCodeState



     fun createNewAccount(userSignupModel: UserSignupModel) {

        authUseCase.userSignupUseCase(userSignupModel).onEach {
            when (it) {
                is Resource.Success -> {
                    _signUpState.value = SignUpState(data = it.data)
                }

                is Resource.Loading -> {
                    _signUpState.value = SignUpState(isLoading = true)
                }

                is Resource.Error -> {
                    _signUpState.value = SignUpState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)

    }

     fun login(userLoginModel: UserLoginModel) {

        authUseCase.userLoginUseCase(userLoginModel).onEach {
            when (it) {
                is Resource.Success -> {
                    _loginState.value = LoginState(data = it.data)
                }

                is Resource.Loading -> {
                    _loginState.value = LoginState(isLoading = true)
                }

                is Resource.Error -> {
                    _loginState.value = LoginState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)

    }

     fun verifyCode(verifyCodeModel: VerifyCodeModel) {

        authUseCase.verifyCodeUseCase(verifyCodeModel).onEach {
            when (it) {
                is Resource.Success -> {
                    _codeVerifyState.value = VerifyCodeState(data = it.data)
                }

                is Resource.Loading -> {
                    _codeVerifyState.value = VerifyCodeState(isLoading = true)
                }

                is Resource.Error -> {
                    _codeVerifyState.value =
                        VerifyCodeState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)

    }

     fun resendCode(phone: String) {

        authUseCase.resendCodeUseCase(phone).onEach {
            when (it) {
                is Resource.Success -> {
                    _resendCodeState.value = ReSendCodeState(data = it.data)
                }

                is Resource.Loading -> {
                    _resendCodeState.value = ReSendCodeState(isLoading = true)
                }

                is Resource.Error -> {
                    _resendCodeState.value =
                        ReSendCodeState(error = it.message ?: "error not found")
                }
            }
        }.launchIn(viewModelScope)

    }


}