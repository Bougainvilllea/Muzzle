package com.example.muzzle.theme


import androidx.compose.ui.graphics.Color
import com.example.muzzle.model.Mode

object EmotionColors {
    fun getBackgroundColor(mode: Mode): Color {
        return when (mode) {
            Mode.HAPPY -> Color(0xFF4C6851)
            Mode.ANGRY -> Color(0xFFB83A2E)
            Mode.CHILL -> Color(0xFFF9AC45)
        }
    }

    val DarkGray = Color(0xFF1E1E1E)
    val HomeBackground = Color(0xFFDDC6A7)
    val MuzzleRed = Color(0xFFA12013)
}