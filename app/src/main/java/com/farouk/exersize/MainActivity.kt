package com.farouk.exersize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.farouk.exersize.base.Connectivity.ConnectivityObserver
import com.farouk.exersize.base.Connectivity.ConnectivityObserver.Status
import com.farouk.exersize.base.Connectivity.NetworkConnectivityObserver
import com.farouk.exersize.base.composables.InfoDialog
import com.farouk.exersize.features.splash.presentaiton.SplashScreen
import com.farouk.exersize.theme.ExersizeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            val infoDialog = remember { mutableStateOf(true) }
            val firstLost = remember { mutableStateOf(true) }
            val status = connectivityObserver.observe().collectAsState(
                initial = Status.Available
            )
            ExersizeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (status.value != Status.Available && infoDialog.value) {
                        InfoDialog(
                            title = "Whoops!",
                            desc = "No Internet Connection found.\n" +
                                    "Check your connection or try again.",
                            onDismiss = {
                                infoDialog.value = false
                                firstLost.value = false
                            }

                        )


                    }

                    if (status.value == Status.Available && !firstLost.value) {
                        InfoDialog(
                            title = "Back Online",
                            desc = "your connection is back again",
                            imgId = R.drawable.outline_wifi_24,
                            onDismiss = {
                                infoDialog.value = true
                                firstLost.value = true
                            }
                        )

                    }
                    LaunchedEffect(key1 = Unit) {
                        if (!infoDialog.value) {
                            lifecycleScope.launch {
                                delay(5000)
                            }.invokeOnCompletion {
                                infoDialog.value = true
                            }
                        }
                    }



                    Navigator(
                        screen = SplashScreen,
                        content = { navigator -> SlideTransition(navigator) })

                }
            }
        }
    }

}



