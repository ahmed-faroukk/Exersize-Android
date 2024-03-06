package com.farouk.exersize.features.authentication.data.remote

import com.farouk.exersize.features.authentication.common.Constants
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginModel
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginResponse
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupModel
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiInterface {

    @FormUrlEncoded
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun userLogin(loginModel: UserLoginModel) : UserLoginResponse

    @FormUrlEncoded
    @POST(Constants.SIGNUP_ENDPOINT)
    suspend fun userSignup(signupModel: UserSignupModel) : UserSignupResponse



}