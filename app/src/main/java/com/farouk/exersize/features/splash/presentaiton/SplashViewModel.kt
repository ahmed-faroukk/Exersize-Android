package com.farouk.exersize.features.splash.presentaiton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.farouk.exersize.features.onBoarding.OnboardingScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {


     fun navigateTo(navigator: Navigator){
        viewModelScope.launch {
            delay(3000)
        }.invokeOnCompletion {
            navigateToOnBoarding(navigator)
        }
    }

    fun navigateToAuth(){

    }
    fun navigateToHome(){

    }

    private fun navigateToOnBoarding(navigator: Navigator){
        navigator.replace(OnboardingScreen())
    }
}