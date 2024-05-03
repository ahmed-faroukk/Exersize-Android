import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farouk.exersize.LocalTopNavigator
import com.farouk.exersize.features.home.data.remote.HomeApiInterface
import com.farouk.exersize.features.home.domain.entity.CoachByIdResponse
import com.farouk.exersize.theme.darkYellow
import com.mala.grad_project.Screenns.CoachScreen.Conmposble.CardOfCustomerReview
import com.mala.grad_project.Screenns.CoachScreen.Conmposble.CircleCoachImage
import com.mala.grad_project.Screenns.CoachScreen.Conmposble.LineTextViewChoachScreen
import com.mala.grad_project.Screenns.CoachScreen.Conmposble.StarRatingBarCoach


@Composable
fun CoachScreenContent(
    showCoachResponse: CoachByIdResponse, onRequestPlanClick: () -> Unit,
) {
    val navigator = LocalTopNavigator.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            val rating by remember { mutableStateOf(1f) }

            // Header Section
            CoachHeader()
            // Coach Info Section
            CoachInfo(
                rating = showCoachResponse.msg.exp.toFloat(),
                name = showCoachResponse.msg.fname + " " + showCoachResponse.msg.lname,
                numOfClints = showCoachResponse.msg.exp.toString(),
                img = showCoachResponse.msg.personal_img
            )
            // Trainee Reviews Section
            Spacer(modifier = Modifier.height(15.dp))
            LineTextViewChoachScreen("Trainee reviews")
            Spacer(modifier = Modifier.height(15.dp))
        }
        item {
            LazyListWithHomeItems(showCoachResponse)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                BlueButton(onClick = {
                    navigator.pop()
                }, modifier = Modifier.width(150.dp), text = "back")
                outlineButton(onClick = {
                    onRequestPlanClick()
                }, modifier = Modifier.width(150.dp), text = "request planning")
            }
            Spacer(modifier = Modifier.height(30.dp))
        }


    }
}

@Composable
fun BlueButton(
    onClick: () -> Unit, modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier, shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = text,
            fontSize = 12.sp, textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}


@Composable
fun outlineButton(
    onClick: () -> Unit, modifier: Modifier = Modifier,
    text: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            Color.White
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp, textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
fun CoachHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 30.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 10.dp),

            )
    }
}

@Composable
fun CoachInfo(rating: Float, name: String, numOfClints: String, img: String) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleCoachImage(rememberAsyncImagePainter(HomeApiInterface.BASE_URL + img), size = 210)
        Text(
            text = "Captain: $name",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        StarRatingBarCoach(
            maxStars = 6,
            rating = rating,
            onRatingChanged = {}
        )

        Text(
            text = "number of clients : $numOfClints ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = darkYellow
        )
        Spacer(modifier = Modifier.height(20.dp))


    }
}

@Composable
fun LazyListWithHomeItems(coach: CoachByIdResponse) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 16.dp)
    ) {
        items(coach.msg.portfolio) { item ->
            CardOfCustomerReview(item)
        }
    }
}
