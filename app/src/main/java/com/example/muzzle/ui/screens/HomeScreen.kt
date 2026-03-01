import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.muzzle.R
import com.example.muzzle.ui.screens.Emotions
import com.example.muzzle.ui.screens.Mode

@Composable
fun HomeScreen() {
    val bellota = FontFamily(
        Font(R.font.bellota, FontWeight.Normal)
    )

    var showEmotionsScreen by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition(label = "blink_transition")
    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink_animation"
    )

    Crossfade(targetState = showEmotionsScreen,
        animationSpec = tween(500)
    ) { targetState ->
        if (targetState) {
            Emotions()
        } else {


    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(color = Color(0xFFDDC6A7))
            )

        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meet...",
                color = Color(0xFF1E1E1E),
                fontSize = 70.sp,
                modifier = Modifier.padding(16.dp),
                fontFamily = bellota
            )
            Text(
                text = "Muzzle!",
                color = Color(0xFFA12013),
                fontSize = 70.sp,
                modifier = Modifier.padding(16.dp),
                fontFamily = bellota
            )
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 700.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { showEmotionsScreen = true },
            modifier = Modifier
                .size(150.dp)
                .alpha(blinkAlpha)
        ) {
            Image(
                painter = painterResource(
                    R.drawable.muz_on),
                contentDescription = "muz on"
            )
        }
    }
    }
    }
}