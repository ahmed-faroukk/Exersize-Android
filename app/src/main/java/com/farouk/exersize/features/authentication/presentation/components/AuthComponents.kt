package com.farouk.exersize.features.authentication.presentation.components

import android.content.res.Resources.Theme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.farouk.exersize.R
import com.farouk.exersize.features.authentication.common.Constants.PIN_VIEW_TYPE_BORDER
import com.farouk.exersize.features.authentication.common.Constants.PIN_VIEW_TYPE_UNDERLINE
import com.farouk.exersize.theme.blue1
import com.farouk.exersize.theme.blue2
import com.farouk.exersize.theme.blue3
import com.farouk.exersize.theme.darkYellow
import kotlinx.coroutines.delay

@Composable
fun RoundedBtn(
    onClick: () -> Unit,
    text: String,
    textColor: Color = blue3,
    buttonColor: Color = darkYellow,
    modifier: Modifier = Modifier,
    textSizeSp: Int = 15,
) {

    Button(
        onClick = {
            onClick()
        }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
            buttonColor
        ), modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 10.dp, top = 10.dp)
    ) {
        AuthText(
            text = text,
            color = textColor,
            fontWeight = FontWeight.ExtraBold,
            sizeWithSp = textSizeSp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AuthText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    sizeWithSp: Int = 20,
    color: Color = MaterialTheme.colorScheme.surface,
    textAlign: TextAlign = TextAlign.Start,

    ) {

    Text(
        text = text,
        color = color,
        modifier = modifier.padding(start = 20.dp),
        fontSize = sizeWithSp.sp,
        fontWeight = fontWeight,
        fontFamily = FontFamily(Font(R.font.roboto_black)),
        //fontFamily = FontFamily(Font(R.font.elmessiri)),
        textAlign = textAlign
    )

}

@Composable
fun AuthLoginText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    sizeWithSp: Int = 20,
    color: Color = MaterialTheme.colorScheme.surface,
    textAlign: TextAlign = TextAlign.Start,

    ) {

    Text(
        text = text,
        color = color,
        modifier = modifier,
        fontSize = sizeWithSp.sp,
        fontWeight = fontWeight,
        fontFamily = FontFamily(Font(R.font.roboto_black)),
        //fontFamily = FontFamily(Font(R.font.elmessiri)),
        textAlign = textAlign
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldString(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    labelColor: Color = MaterialTheme.colorScheme.surface,
    value: MutableState<String> = remember {
        mutableStateOf("")
    },
) {

    var text by remember { mutableStateOf(TextFieldValue(value.value)) }

    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = labelColor) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = blue2,
            unfocusedIndicatorColor = labelColor,
            containerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.surface,

            ), modifier = modifier, singleLine = true,
        keyboardOptions = keyboardOptions

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldNumber(
    onNameChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
    labelColor: Color = MaterialTheme.colorScheme.surface,

    ) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Box {

        OutlinedTextField(
            value = text,
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AuthLoginText(text = "+20  ", sizeWithSp = 15)
                    Text(text = label, color = labelColor)
                }
            },
            onValueChange = {
                text = it
                onNameChange(it.text)
            }, colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = labelColor,
                containerColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.surface,


            ), modifier = modifier, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError
        )
    }


}

@Composable
fun PinView(
    pinText: String,
    onPinTextChange: (String) -> Unit,
    digitColor: Color = MaterialTheme.colorScheme.surface,
    digitSize: TextUnit = 20.sp,
    containerSize: Dp = digitSize.value.dp * 2,
    digitCount: Int = 4,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
    unfoucsUnderLineColor: Color = MaterialTheme.colorScheme.surface,
    foucsUnderLineColor: Color = blue1,


    ) {
    BasicTextField(value = pinText,
        onValueChange = onPinTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(digitCount) { index ->
                    DigitView(
                        index,
                        pinText,
                        digitColor,
                        digitSize,
                        containerSize,
                        type = type,
                        unfoucsUnderLineColor = unfoucsUnderLineColor,
                        foucsUnderLineColor = foucsUnderLineColor
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        })
}


@Composable
private fun DigitView(
    index: Int,
    pinText: String,
    digitColor: Color,
    digitSize: TextUnit,
    containerSize: Dp,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
    unfoucsUnderLineColor: Color = MaterialTheme.colorScheme.surface,
    foucsUnderLineColor: Color = blue1,

    ) {
    val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
        Modifier
            .size(containerSize)
            .border(
                width = 1.dp,
                color = digitColor,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(bottom = 3.dp)
    } else Modifier.width(containerSize)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthLoginText(
            text = if (index >= pinText.length) "" else pinText[index].toString(),
            color = digitColor,
            modifier = modifier,
            textAlign = TextAlign.Center,
            sizeWithSp = 25
        )
        if (type == PIN_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(if (index >= pinText.length) unfoucsUnderLineColor else foucsUnderLineColor)
                    .height(2.dp)
                    .width(containerSize)
            )
        }
    }
}


@Composable
fun LoadingDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // onDismissRequest =  LoadingDialog.value = false
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface , shape = RoundedCornerShape(16.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AFLoading(
                    color1 = MaterialTheme.colorScheme.primary,
                    color2 = MaterialTheme.colorScheme.primary,
                    color3 = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit,
    title: String,
    desc: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(imageVector = Icons.Rounded.Warning, contentDescription = "")
                AuthText(
                    text = title,
                    modifier = Modifier.padding(16.dp),
                )
                AuthText(
                    text = desc,
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        }
    }
}


@Composable
fun AFLoading(
    // Modifier for customizing the layout of the loading animation
    modifier: Modifier = Modifier,

    // Size of each circle in the loading animation
    circleSize: Dp = 20.dp,

    // Space between the circles in the loading animation
    spaceBetween: Dp = 10.dp,

    // Vertical travel distance of each circle during animation
    travelDistance: Dp = 20.dp,

    // Duration of the animation cycle for each circle
    animationDuration: Int = 1200,

    // Delay between the start of animations for consecutive circles
    delayBetweenCircles: Int = 100,

    // Colors for the circles
    color1: Color = Color.Yellow,
    color2: Color = Color.Cyan,
    color3: Color = Color.White,

    // Repeat mode for the animation (e.g., Restart, Reverse)
    repeatMode: RepeatMode = RepeatMode.Restart,

    // Easing function for the animation
    easing: Easing = LinearOutSlowInEasing
) {
    val circles = List(3) { index ->
        remember { Animatable(initialValue = 0f) }
            .apply {
                LaunchedEffect(key1 = this) {
                    delay(index * delayBetweenCircles.toLong())
                    animateTo(
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = keyframes {
                                durationMillis = animationDuration
                                0.0f at 0 with easing
                                1.0f at (animationDuration / 4) with easing
                                0.0f at (animationDuration / 2) with easing
                                0.0f at animationDuration with easing
                            },
                            repeatMode = repeatMode
                        )
                    )
                }
            }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEachIndexed() { index, value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = when (index) {
                            0 -> color1
                            1 -> color2
                            else -> color3
                        },
                        shape = CircleShape
                    )
            )
        }
    }
}