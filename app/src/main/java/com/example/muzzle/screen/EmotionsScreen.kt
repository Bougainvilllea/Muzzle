package com.example.muzzle.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.muzzle.R
import com.example.muzzle.animation.rememberBlinkingAnimation
import com.example.muzzle.animation.rememberEmotionTransition
import com.example.muzzle.components.buttons.EmotionButton
import com.example.muzzle.components.buttons.MuteButtons
import com.example.muzzle.components.face.EmotionFace
import com.example.muzzle.components.text.EmotionLabels
import com.example.muzzle.model.Mode
import com.example.muzzle.theme.EmotionColors

@Composable
fun EmotionsScreen() {
    val bellota = FontFamily(
        Font(R.font.bellota, FontWeight.Normal)
    )

    var selectMode by remember { mutableStateOf(Mode.CHILL) }
    var boxVisible by remember { mutableStateOf(true) }
    var blinkingMode by remember { mutableStateOf<Mode?>(null) }

    val blinkAlpha = rememberBlinkingAnimation()
    val animationValues = rememberEmotionTransition(selectMode)

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(EmotionColors.getBackgroundColor(selectMode))
            )
        }
    }

    EmotionLabels(
        blinkingMode = blinkingMode,
        blinkAlpha = blinkAlpha,
        fontFamily = bellota
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        EmotionButton(
            mode = Mode.ANGRY,
            currentMode = selectMode,
            onClick = {
                selectMode = Mode.ANGRY
                blinkingMode = Mode.ANGRY
            }
        )
        EmotionButton(
            mode = Mode.CHILL,
            currentMode = selectMode,
            onClick = {
                selectMode = Mode.CHILL
                blinkingMode = Mode.CHILL
            }
        )
        EmotionButton(
            mode = Mode.HAPPY,
            currentMode = selectMode,
            onClick = {
                selectMode = Mode.HAPPY
                blinkingMode = Mode.HAPPY
            }
        )
    }

    EmotionFace(
        isVisible = boxVisible,
        mode = selectMode,
        circleRadius = animationValues.circleRadiusDp,
        sweepAngle = animationValues.sweepAngle,
        modifier = Modifier
    )

    MuteButtons(
        isVisible = boxVisible,
        onMuteOn = { boxVisible = true },
        onMuteOff = { boxVisible = false }
    )
}