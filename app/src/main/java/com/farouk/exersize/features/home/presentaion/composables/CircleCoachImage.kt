package com.farouk.exersize.features.home.presentaion.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.farouk.exersize.base.animations.AFAnimateAsFloat
import com.farouk.exersize.theme.darkYellow

@Composable
fun CircleCoachImage(
    painter: Painter,
    size:Int
) {
    val borderWidth = 2.dp

    Box (contentAlignment = Alignment.TopStart){
        AFAnimateAsFloat(
            delay = 500,
            initValue = 20f,
            targetValue = 90f,
            animationSpec = tween(100),
            content = { modifier, isVisible ->
                if (isVisible.value)
                    Image(
                        painter = painter,
                        contentDescription = "error image",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .size(size.dp)
                            .padding(borderWidth)
                            .border(
                                BorderStroke(borderWidth, darkYellow),
                                CircleShape
                            )
                            .clip(CircleShape)
                    )
            } )



    }
}