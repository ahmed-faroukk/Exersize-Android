package com.farouk.exersize.features.home.presentaion

import SetupNavBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.farouk.exersize.theme.ExersizeTheme

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        SetupNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ExersizeTheme {
        Navigator(
            screen = HomeScreen(),
            content = { navigator -> SlideTransition(navigator) })
    }
}