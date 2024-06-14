
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ComponentRegistry
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.farouk.exersize.base.Constants.BASE_URL
import com.farouk.exersize.features.plan.domain.Entitiy.Exercise
import com.farouk.exersize.theme.blue2
import com.farouk.exersize.theme.brightYellow

@Composable
fun ExerSizeCard(
 exersize: Exercise
){
    var visible by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(30.dp))
            .shadow(elevation = 10.dp)
            , colors = CardDefaults.cardColors(Color.White)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){

            Box (
                Modifier.padding(top=30.dp)
            ){
                GifImage(BASE_URL+"api/img/"+exersize.exercise,visible = true)

            }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(200.dp,150.dp)
                        .padding(20.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                        ,
                        text =exersize.name,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color= blue2
                    )
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            modifier = Modifier,
                            text ="number of repetion ",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize(),
                            text ="${exersize.times}",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color= brightYellow
                        )
                    }
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            modifier = Modifier,
                            text ="Break time ",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .wrapContentSize(),
                            text ="  ${exersize.rest }",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color= brightYellow
                        )
                    }
                }

            }

        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))

    }
}
@Composable
fun GifImage(
    exersize: String,
    visible: Boolean
) {
    val density = LocalDensity.current
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components(fun ComponentRegistry.Builder.() {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        })
        .build()

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { with(density) { -40.dp.roundToPx() } }
        ) + expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Image(
            painter = rememberImagePainter(
                data = exersize,
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(blue2)
        )
    }
}

