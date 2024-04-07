package com.farouk.exersize.features.inbody.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.farouk.exersize.features.authentication.presentation.components.AuthText
import com.farouk.exersize.theme.blue3
import com.farouk.exersize.theme.darkYellow

@JvmOverloads
@Composable
fun InBodyErrorDialog(
    title: String = "Message",
    desc: String = "Your Message",
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        blue3,
                        shape = RoundedCornerShape(16.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AuthText(
                        text = title ,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp), sizeWithSp = 15
                    )
                    AuthText(
                        text = desc,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp) , sizeWithSp = 13,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss" , color = darkYellow)
                    }
                }
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

}


