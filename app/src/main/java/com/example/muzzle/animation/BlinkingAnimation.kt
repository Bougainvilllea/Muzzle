package com.example.muzzle.animation

import androidx.compose.animation.core.*
import androidx.compose.runtime.*

@Composable
fun rememberBlinkingAnimation(
    initialValue: Float = 1f,
    targetValue: Float = 0.3f,
    durationMillis: Int = 500
): Float {
    val infiniteTransition = rememberInfiniteTransition(label = "blink_transition")
    return infiniteTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink_animation"
    ).value
}