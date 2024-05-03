package com.mala.grad_project.Screenns.CoachScreen.Conmposble

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farouk.exersize.R
import com.farouk.exersize.features.home.data.remote.HomeApiInterface
import com.farouk.exersize.features.home.domain.entity.Portfolio
import com.farouk.exersize.theme.darkYellow

@Composable
fun CardOfCustomerReview(
    items: Portfolio ,
){
    var rating by remember { mutableStateOf(1f) }
        Column(
            modifier = Modifier
                .size(250.dp, height = 210.dp)
                .padding(16.dp)
                .shadow(elevation = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            ) {
                Column {

                    Row(
                        Modifier.fillMaxWidth()
                    ) {
                        CircleCoachImage(
                             rememberAsyncImagePainter(HomeApiInterface.BASE_URL+items.img_before), 50
                        )
                        CircleCoachImage(
                            rememberAsyncImagePainter(HomeApiInterface.BASE_URL+items.img_after), 50
                        )


                        Column {
                            Text(
                                text = items.description,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                            )
                            StarReview(maxStars =6, rating = 5f, onRatingChanged = {
                                rating = it
                            },20,10 )
                        }

                    }


                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)

                    .background(
                        darkYellow,
                        RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
            ){
                Text(
                    text = "${items.description} ",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                     color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(start = 10.dp, end = 10.dp, top = 20.dp)

                )
            }
        }
    }





@Composable
fun blueSheapOfCard(){
    Image(
        painter = painterResource(id = R.drawable.chat )
        , contentDescription =null,
        Modifier.size(width = 200.dp, height = 90.dp)
    )
}