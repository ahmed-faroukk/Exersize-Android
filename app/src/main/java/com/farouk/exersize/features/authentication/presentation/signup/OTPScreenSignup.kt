package com.farouk.exersize.features.authentication.presentation.signup

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.core.screen.Screen
import com.farouk.exersize.features.authentication.common.Constants
import com.farouk.exersize.features.authentication.presentation.components.AuthText
import com.farouk.exersize.features.authentication.presentation.components.PinView
import com.farouk.exersize.features.authentication.presentation.components.RoundedBtn
import com.farouk.exersize.theme.blue1
import kotlinx.coroutines.delay

class OTPScreenSignup : Screen {
    @Composable
    override fun Content() {
        OTPSignup()
    }
    @Composable
    fun OTPSignup(){
        CodeVerificationScreen("010")
    }

}


@Composable
fun CodeVerificationScreen(
    phone: String,
) {

    val context = LocalContext.current
    val isVibrating = remember { mutableStateOf(true) }
    val vibrator: Vibrator? = ContextCompat.getSystemService(context, Vibrator::class.java)
    val isShaking = remember { mutableStateOf(false) }
    val isVisible = remember { mutableStateOf(false) }
    val ableToSend = remember { mutableStateOf(true) }
    val lineColor = remember {
        mutableStateOf(blue1)
    }
    var (pinValue, onPinValueChange) = remember {
        mutableStateOf("")
    }
    val infoDialog = remember { mutableStateOf(true) }
    val translationXState by animateFloatAsState(
        targetValue = if (isShaking.value) 15f else 0f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }
    LaunchedEffect(isShaking.value) {

        if (isShaking.value) {
            delay(100)
            isShaking.value = false
        }
    }





    if (pinValue.length == 4 && ableToSend.value) {
        // viewModel.verifyUser(pinValue, phone)
        isVibrating.value = false
        ableToSend.value = false

    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {


        // 1
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            visible = isVisible.value,
            enter = scaleIn(tween(700)) + fadeIn(tween(1000))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)
                    )
                    .padding(20.dp)
            ) {


                Spacer(modifier = Modifier.height(30.dp))


                AuthText(
                    text = "Verfied Your Number",
                    sizeWithSp = 20,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))

                AuthText(
                    text = "We sent you a code, please enter it",
                    sizeWithSp = 15,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Start

                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = if (isShaking.value) translationXState.dp else 0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    println(isShaking.value)
                    PinView(
                        pinText = pinValue,
                        unfoucsUnderLineColor = lineColor.value,
                        onPinTextChange = onPinValueChange,
                        type = Constants.PIN_VIEW_TYPE_BORDER,
                        containerSize = 35.dp
                    )

                }
                Spacer(modifier = Modifier.height(150.dp))
                RoundedBtn(onClick = {}, text = "Continue")

                /* if (state.error.isNotEmpty()) {
                   if (infoDialog.value) {
                       InfoDialog(title = "error please try again later ",
                           desc = state.error,
                           imgId = R.drawable.baseline_warning_amber_24,
                           onDismiss = {
                               infoDialog.value = false
                           })
                   }
               }
               if (state.isLoading) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center
                   ) {
                       Spacer(modifier = Modifier.height(30.dp))

                       CustomLoading(
                           circleSize = 12.dp,
                           spaceBetween = 5.dp,
                           travelDistance = 10.dp
                       )

                   }

               }*/


            }
        }



    }

}

fun vibrateApp(vibrator: Vibrator?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator?.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
    } else {
        //deprecated in API 26
        vibrator?.vibrate(300);
    }
}

