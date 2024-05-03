package com.farouk.exersize.features.home.presentaion.Packages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.farouk.exersize.features.authentication.presentation.components.AFLoading
import com.farouk.exersize.features.authentication.presentation.components.ErrorDialog
import com.farouk.exersize.features.home.presentaion.HomeViewModel
import com.farouk.exersize.features.inbody.presentation.screens.GifImage
import com.mala.grad_project.Screenns.planCard.PackagesUI
import kotlinx.coroutines.delay

class PackagesScreen(val id : String ,val coachName : String ,val coachImg : String ,val NOC : String) : Screen {
    @Composable
    override fun Content() {
        val isVisible = remember {
            mutableStateOf(false)
        }

        val size by animateFloatAsState(
            targetValue = if (!isVisible.value) 0f else 200f,
            label = "", animationSpec = tween(1000)
        )

        LaunchedEffect(isVisible.value) {
            // Hide the box for 200 milliseconds
            if (!isVisible.value) {
                delay(300)
                isVisible.value = true
            }
        }


        val viewmodel: HomeViewModel = getViewModel()
        val state = viewmodel.getPackages.value
        val req = viewmodel.reqPackages.value

        LaunchedEffect(key1 = Unit) {
            viewmodel.getPackages(id)
        }
        val dialog = remember {
            mutableStateOf(false)
        }

        when {
            state.isLoading -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AFLoading(
                        color1 = MaterialTheme.colorScheme.surface,
                        color2 = MaterialTheme.colorScheme.surface,
                        color3 = MaterialTheme.colorScheme.surface
                    )
                }

            }

            state.error.isNotEmpty() -> {
                dialog.value = true
            }

            dialog.value -> {
                ErrorDialog(onDismissRequest = {
                    dialog.value = false
                }, title = "Error", desc = state.error.toString())
            }
        }

        when {
            req.isLoading -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AFLoading(
                        color1 = MaterialTheme.colorScheme.surface,
                        color2 = MaterialTheme.colorScheme.surface,
                        color3 = MaterialTheme.colorScheme.surface
                    )
                }

            }

            req.error.isNotEmpty() -> {
                dialog.value = true
            }
            req.data?.status == true -> {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val scope = rememberCoroutineScope()
                    GifImage()
                }

            }

            dialog.value -> {
                ErrorDialog(onDismissRequest = {
                    dialog.value = false
                }, title = "Error", desc = req.error.toString())
            }
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth(),
            visible = isVisible.value,
            enter = slideInVertically(
                initialOffsetY = { 1000 }, animationSpec = tween(2000)
            ) + fadeIn(),
        ){
            state.data?.msg?.let { PackagesUI(
                packages = it,
                coachName = coachName,
                coachImg = coachImg,
                numOfClints = NOC
            ){
                viewmodel.reqPackages(coachId = id , packageId =  it.toString()  )
            }
            }
        }


    }
}

