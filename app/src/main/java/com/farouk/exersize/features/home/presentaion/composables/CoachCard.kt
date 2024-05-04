package com.farouk.exersize.features.home.presentaion.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farouk.exersize.theme.darkYellow
import com.mala.grad_project.Screenns.CoachScreen.Conmposble.StarRatingBarCoach

@Composable
fun CoachCard(
    clintNum : String,
    name : String,
    img : String,
    rate : Float,
    modifier: Modifier ,

    onClick : ()  -> Unit ,
) {

    var rating by remember { mutableStateOf(1f) }

    Box(modifier = modifier
        .fillMaxWidth()
        .padding(25.dp)
        .shadow(elevation = 20.dp)

    )
    {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), modifier = modifier
                .height(110.dp)
                .clickable {
                    onClick()
                }
        )
        {
            Text(
                text = "Captain : $name",
                Modifier
                    .padding(start = 100.dp, top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold ,
                color = MaterialTheme.colorScheme.surface
            )
            Spacer(modifier = Modifier.height(1.dp))
            ShowNumberofClients(number = clintNum , Modifier.padding(start = 50.dp))
            StarRatingBarCoach(maxStars = 5, rating = rate, onRatingChanged = {} , paddingStart = 90)

        }
        CircleCoachImage(painter = rememberAsyncImagePainter(img),100)

    }


}
@Composable
fun ShowNumberofClients(
    number:String,
    modifier: Modifier
){
    Row (
        modifier
    ){
        Text(
            text = "number of clients : ",
            Modifier
                .padding(start=60.dp),
            textAlign = TextAlign.Start,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = darkYellow
        )
        Text(modifier = Modifier,
            text = "$number",
            fontSize =10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
        )
    }
}

