package com.farouk.exersize.features.authentication.presentation.CodeVerfication

import com.farouk.exersize.features.authentication.data.Model.codeVerfication.VerifyCodeResponse
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginResponse
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupResponse

data class VerifyCodeState(
    val data : VerifyCodeResponse ?= null   ,
    val isLoading : Boolean = false ,
    val error : String = ""
)
