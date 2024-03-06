package com.farouk.exersize.features.authentication.presentation.signup

import android.content.res.Resources.Theme
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.farouk.exersize.features.authentication.common.Constants.PIN_VIEW_TYPE_UNDERLINE
import com.farouk.exersize.features.authentication.presentation.components.AuthLoginText
import com.farouk.exersize.features.authentication.presentation.components.AuthText
import com.farouk.exersize.features.authentication.presentation.components.OutLineTextFieldNumber
import com.farouk.exersize.features.authentication.presentation.components.OutLineTextFieldString
import com.farouk.exersize.features.authentication.presentation.components.PinView
import com.farouk.exersize.features.authentication.presentation.components.RoundedBtn
import com.farouk.exersize.features.authentication.presentation.login.LoginScreen
import com.farouk.exersize.theme.blue1
import com.farouk.exersize.theme.blue3
import com.farouk.exersize.theme.darkYellow
import kotlinx.coroutines.delay

class SignupScreen : Screen {

    @Composable
    override fun Content() {
        Signup()
    }

    @Composable
    fun Signup() {
        val navigator = LocalNavigator.currentOrThrow

        val firstName = remember {
            mutableStateOf("")
        }
        val lastName = remember {
            mutableStateOf("")
        }
        val phone = remember {
            mutableStateOf("")
        }
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(Modifier.fillMaxWidth()) {
                item {
                    AuthText(
                        text = "Create your Account",
                        sizeWithSp = 24,
                    )
                    AuthText(
                        text = "Enter your details to continue",
                        sizeWithSp = 16,
                        color = blue3,
                        fontWeight = FontWeight.Thin
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    AuthText(text = "First name", sizeWithSp = 16)
                    OutLineTextFieldString(
                        onNameChange = { firstName.value = it },
                        label = "enter your first name"
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AuthText(text = "Last name", sizeWithSp = 16)
                    OutLineTextFieldString(
                        onNameChange = { lastName.value = it },
                        label = "enter your last name"
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AuthText(text = "Mobile number", sizeWithSp = 16)
                    OutLineTextFieldNumber(
                        onNameChange = { phone.value = it },
                        label = "enter your phone number",
                    )
                }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                RoundedBtn(onClick = { navigator.push(OTPScreenSignup())}, text = "Create an account")
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    AuthText(
                        text = "Already a member?",
                        sizeWithSp = 14,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surface
                    )
                    AuthLoginText(
                        text = "Login",
                        sizeWithSp = 14,
                        color = darkYellow,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            navigator.push(LoginScreen())
                        })
                }
            }


        }
        }
    }

}
