package com.farouk.exersize.features.authentication.data.repository

import com.farouk.exersize.features.authentication.data.remote.AuthApiInterface
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginModel
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginResponse
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupModel
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupResponse
import com.farouk.exersize.features.authentication.domain.repository.AuthRepo
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authApiInterface: AuthApiInterface
) : AuthRepo {
    override suspend fun userLogin(userLoginModel: UserLoginModel): UserLoginResponse =
        authApiInterface.userLogin(userLoginModel)

    override suspend fun userSignup(userSignupModel: UserSignupModel): UserSignupResponse =
        authApiInterface.userSignup(userSignupModel)
}