package com.farouk.exersize.features.authentication.presentation.CodeVerfication

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.Animatable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.farouk.exersize.R
import com.farouk.exersize.features.authentication.common.Constants
import com.farouk.exersize.features.authentication.data.Model.codeVerfication.VerifyCodeModel
import com.farouk.exersize.features.authentication.presentation.AuthViewModel
import com.farouk.exersize.features.authentication.presentation.components.AuthText
import com.farouk.exersize.features.authentication.presentation.components.ErrorDialog
import com.farouk.exersize.features.authentication.presentation.components.LoadingDialog
import com.farouk.exersize.features.authentication.presentation.components.PinView
import com.farouk.exersize.features.authentication.presentation.components.RoundedBtn
import com.farouk.exersize.features.profile.ProfileScreen
import com.farouk.exersize.features.profile.Screen
import com.farouk.exersize.theme.blue1
import com.farouk.exersize.theme.darkYellow
import kotlinx.coroutines.delay

class CodeVerificationScreen(val phone : String) : Screen {
    @Composable
    override fun Content() {
        val viewModel : AuthViewModel = getViewModel()
        OTPLogin(viewModel , phone)
    }



}


@Composable
fun OTPLogin(
    viewModel: AuthViewModel , phone :String
) {

    val context = LocalContext.current
    val vibrator: Vibrator? = ContextCompat.getSystemService(context, Vibrator::class.java)
    val isShaking = remember { mutableStateOf(false) }
    val isVisible = remember { mutableStateOf(false) }
    val lineColor = remember {
        Animatable(blue1)
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
    val navigator = LocalNavigator.currentOrThrow

    val code = remember {
        mutableStateOf("")
    }
    val state = viewModel.codeVerifyState.value


    if (state.isLoading) {
        LaunchedEffect(Unit) {
            lineColor.animateTo(blue1, animationSpec = tween(500))
            lineColor.animateTo(darkYellow, animationSpec = tween(500))
        }
    }


     if (state.data?.data?.account == "new") {
    }else{
        vibrateApp(vibrator)
        LaunchedEffect(Unit) {
            lineColor.animateTo(Color.Red, animationSpec = tween(500))
        }
    }


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
                    text = stringResource(R.string.verfied_your_number),
                    sizeWithSp = 20,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.surface,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))

                AuthText(
                    text = stringResource(R.string.we_sent_you_a_code_please_enter_it),
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
                RoundedBtn(onClick = {

                    navigator.replaceAll(ProfileScreen())

                    //    viewModel.verifyCode(VerifyCodeModel(phone , code.value))
                }, text = stringResource(R.string.continue_btn))



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

