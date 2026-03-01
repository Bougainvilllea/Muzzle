package com.example.muzzle.components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.muzzle.model.Mode

@Composable
fun EmotionLabels(
    blinkingMode: Mode?,
    blinkAlpha: Float,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        EmotionLabel(
            text = "angry",
            isBlinking = blinkingMode == Mode.ANGRY,
            blinkAlpha = blinkAlpha,
            fontFamily = fontFamily
        )
        EmotionLabel(
            text = "chill",
            isBlinking = blinkingMode == Mode.CHILL,
            blinkAlpha = blinkAlpha,
            fontFamily = fontFamily
        )
        EmotionLabel(
            text = "happy",
            isBlinking = blinkingMode == Mode.HAPPY,
            blinkAlpha = blinkAlpha,
            fontFamily = fontFamily
        )
    }
}

@Composable
fun EmotionLabel(
    text: String,
    isBlinking: Boolean,
    blinkAlpha: Float,
    fontFamily: FontFamily
) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = if (isBlinking)
            Color(0x20000000).copy(alpha = blinkAlpha)
        else
            Color(0x50000000),
        fontFamily = fontFamily
    )
}