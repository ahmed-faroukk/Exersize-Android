package com.mala.grad_project.Screenns.CoachScreen.Conmposble

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.farouk.exersize.theme.darkYellow

@Composable
fun LineTextViewChoachScreen(
    text:String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ){
        singleHorizontalLine(140)
        Text(
            text =text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color= darkYellow,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        singleHorizontalLine(140)
    }
}
@Preview
@Composable
fun show(){
LineTextViewChoachScreen("hello")
}