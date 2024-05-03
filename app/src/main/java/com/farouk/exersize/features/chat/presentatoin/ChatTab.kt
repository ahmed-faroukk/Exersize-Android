package com.farouk.exersize.features.menu.presentatoin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.farouk.exersize.R

object ChatTab : Tab {

    override val options: TabOptions
    @Composable
    get() {
        val title = "Home"
        val icon = painterResource(R.drawable.chat)

        return remember {
            TabOptions(
                index = 3u,
                title = title,
                icon = icon
            )
        }
    }

    @Composable
    override fun Content() {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Chat")
        }
    }
}