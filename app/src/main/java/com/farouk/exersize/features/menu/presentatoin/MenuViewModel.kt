package com.farouk.exersize.features.menu.presentatoin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.farouk.exersize.features.onBoarding.OnboardingScreen
import com.farouk.exersize.user.data.local.UserLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val user: UserLocalDataSource,
    ) : ViewModel() {

    fun updateUserData(navigator: Navigator) {
        viewModelScope.launch {
            user.setLoggedout()
            navigator.replaceAll(OnboardingScreen())
        }
    }
}