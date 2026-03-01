package com.example.muzzle.screen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.muzzle.R
import com.example.muzzle.animation.rememberBlinkingAnimation
import com.example.muzzle.theme.EmotionColors

@Composable
fun HomeScreen() {
    val bellota = FontFamily(
        Font(R.font.bellota, FontWeight.Normal)
    )

    var showEmotionsScreen by remember { mutableStateOf(false) }
    val blinkAlpha = rememberBlinkingAnimation(
        initialValue = 1f,
        targetValue = 0.7f,
        durationMillis = 1000
    )

    Crossfade(
        targetState = showEmotionsScreen,
        animationSpec = tween(500)
    ) { targetState ->
        if (targetState) {
            EmotionsScreen()
        } else {
            HomeContent(
                fontFamily = bellota,
                blinkAlpha = blinkAlpha,
                onMuzzleClick = { showEmotionsScreen = true }
            )
        }
    }
}

@Composable
fun HomeContent(
    fontFamily: FontFamily,
    blinkAlpha: Float,
    onMuzzleClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = EmotionColors.HomeBackground)
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Meet...",
                color = EmotionColors.DarkGray,
                fontSize = 70.sp,
                modifier = Modifier.padding(16.dp),
                fontFamily = fontFamily
            )
            Text(
                text = "Muzzle!",
                color = EmotionColors.MuzzleRed,
                fontSize = 70.sp,
                modifier = Modifier.padding(16.dp),
                fontFamily = fontFamily
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = onMuzzleClick,
                modifier = Modifier
                    .size(150.dp)
                    .alpha(blinkAlpha)
            ) {
                Image(
                    painter = painterResource(R.drawable.muz_on),
                    contentDescription = "muz on"
                )
            }
        }
    }
}