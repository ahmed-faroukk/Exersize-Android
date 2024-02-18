package com.farouk.exersize.base.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.farouk.exersize.base.Connectivity.ConnectivityObserver
import com.farouk.exersize.base.Connectivity.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
): ViewModel() {

    private val _onlineNow = mutableStateOf(false)
    val onlineNow: State<Boolean> get() = _onlineNow


    private fun handleConnectivityStatus(status: ConnectivityObserver.Status) {
        when (status) {
            ConnectivityObserver.Status.Available -> {
                    _onlineNow.value = true
            }
            else -> {
                    _onlineNow.value = false
            }
        }
    }

}