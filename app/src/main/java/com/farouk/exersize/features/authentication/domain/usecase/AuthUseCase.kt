package com.farouk.exersize.features.authentication.domain.usecase

data class AuthUseCase (
    private val userLoginUseCase: UserLoginUseCase,
    private val userSignupUseCase: UserSignupUseCase
)
