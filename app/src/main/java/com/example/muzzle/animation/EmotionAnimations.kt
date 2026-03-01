package com.example.muzzle.animation

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.muzzle.model.Mode

@Composable
fun rememberEmotionTransition(selectMode: Mode): EmotionAnimationValues {
    val transition = updateTransition(
        targetState = selectMode,
        label = "emotions_transition"
    )

    val circleRadiusDp by transition.animateDp(
        transitionSpec = { tween(durationMillis = 500) },
        label = "radius"
    ) { mode ->
        when (mode) {
            Mode.ANGRY, Mode.CHILL -> 60.dp
            Mode.HAPPY -> 70.dp
        }
    }

    val sweepAngle by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) },
        label = "sweepAngle"
    ) { mode ->
        when (mode) {
            Mode.ANGRY -> 180f
            Mode.CHILL, Mode.HAPPY -> 360f
        }
    }

    return EmotionAnimationValues(circleRadiusDp, sweepAngle)
}

data class EmotionAnimationValues(
    val circleRadiusDp: Dp,
    val sweepAngle: Float
)