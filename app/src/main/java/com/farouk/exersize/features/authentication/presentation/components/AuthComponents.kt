package com.farouk.exersize.features.authentication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.farouk.exersize.R
import com.farouk.exersize.features.authentication.common.Constants.PIN_VIEW_TYPE_BORDER
import com.farouk.exersize.features.authentication.common.Constants.PIN_VIEW_TYPE_UNDERLINE
import com.farouk.exersize.theme.blue1
import com.farouk.exersize.theme.blue2
import com.farouk.exersize.theme.darkYellow

@Composable
fun RoundedBtn(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Color.Black,
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
    keyboardOptions : KeyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Text),
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
            containerColor = Color.Transparent ,
            cursorColor = MaterialTheme.colorScheme.surface,

            ), modifier = modifier, singleLine = true ,
        keyboardOptions = keyboardOptions

    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldNumber(
    onNameChange: (String) -> Unit,
    label: String,
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
                Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Center) {
                    AuthLoginText(text = "+20  " , sizeWithSp = 15)
                    Text(text = label, color = labelColor)
                }
                    },
            onValueChange = {
                text = it
                onNameChange(it.text)
            }, colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedIndicatorColor = labelColor,
                containerColor = Color.Transparent

            ), modifier = modifier, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
            Row(horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically) {
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

