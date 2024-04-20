package com.farouk.exersize.base.navigation

sealed class AppRoutes {
    data object SplashScreen : AppRoutes()
    data object OnboardingScreen : AppRoutes()


}