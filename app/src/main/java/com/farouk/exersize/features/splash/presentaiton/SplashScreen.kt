package com.farouk.exersize.features.splash.presentaiton

import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.farouk.exersize.R
import com.farouk.exersize.base.animations.AFAnimateAsFloat
import com.farouk.exersize.features.splash.presentaiton.components.ShowLogo

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {

    AFAnimateAsFloat(
        delay = 300,
        initValue = 400f,
        targetValue = 300f,
        animationSpec = tween(100),
        content = { modifier , isVisible ->
        ShowLogo(modifier = modifier , R.drawable.sec_logo , 400f)
        if (isVisible.value)
        ShowLogo(modifier = modifier , R.drawable.main_logo , 400f)
    })
}

