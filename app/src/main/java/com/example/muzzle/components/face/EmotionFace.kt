package com.example.muzzle.components.face

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.muzzle.model.Mode
import com.example.muzzle.theme.EmotionColors

@Composable
fun EmotionFace(
    isVisible: Boolean,
    mode: Mode,
    circleRadius: Dp,
    sweepAngle: Float,
    modifier: Modifier = Modifier
) {
    val browAngle by animateFloatAsState(
        targetValue = when (mode) {
            Mode.HAPPY -> -30f
            Mode.CHILL -> 0f
            Mode.ANGRY -> 30f
        },
        label = "browAngle"
    )

    val mouthCurve by animateFloatAsState(
        targetValue = when (mode) {
            Mode.HAPPY -> -50f
            Mode.CHILL -> 0f
            Mode.ANGRY -> 50f
        },
        label = "mouthCurve"
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Box(
            modifier = modifier
                .drawBehind {
                    val canvasWidth = size.width
                    val circleRadiusPx = circleRadius.toPx()
                    val centerX = canvasWidth / 2f
                    val eyesDistance = canvasWidth * 0.4f
                    val firstCircleX = centerX - eyesDistance / 2f
                    val secondCircleX = centerX + eyesDistance / 2f
                    val centerY = size.height

                    drawBrows(firstCircleX, secondCircleX, centerY, circleRadiusPx, browAngle)
                    drawEyes(firstCircleX, secondCircleX, centerY, circleRadiusPx, sweepAngle)
                    drawMouth(centerX, centerY, circleRadiusPx, mouthCurve)
                }
                .animateContentSize()
                .fillMaxWidth()
                .height(450.dp)
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawBrows(
    firstCircleX: Float,
    secondCircleX: Float,
    centerY: Float,
    circleRadius: Float,
    browAngle: Float
) {
    val browWidth = 250f
    val browHeight = 100f
    val browOffset = 50f

    rotate(
        degrees = browAngle,
        pivot = Offset(firstCircleX, centerY - circleRadius)
    ) {
        drawRoundRect(
            color = EmotionColors.DarkGray,
            topLeft = Offset(
                x = firstCircleX - browWidth / 2f,
                y = centerY - circleRadius - browOffset - browHeight
            ),
            size = Size(browWidth, browHeight),
            cornerRadius = CornerRadius(browHeight / 2f, browHeight / 2f)
        )
    }

    rotate(
        degrees = -browAngle,
        pivot = Offset(secondCircleX, centerY - circleRadius)
    ) {
        drawRoundRect(
            color = EmotionColors.DarkGray,
            topLeft = Offset(
                x = secondCircleX - browWidth / 2f,
                y = centerY - circleRadius - browOffset - browHeight
            ),
            size = Size(browWidth, browHeight),
            cornerRadius = CornerRadius(browHeight / 2f, browHeight / 2f)
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawEyes(
    firstCircleX: Float,
    secondCircleX: Float,
    centerY: Float,
    circleRadius: Float,
    sweepAngle: Float
) {
    drawArc(
        color = EmotionColors.DarkGray,
        startAngle = 0f,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(
            x = firstCircleX - circleRadius,
            y = centerY - circleRadius
        ),
        size = Size(circleRadius * 2, circleRadius * 2)
    )

    drawArc(
        color = EmotionColors.DarkGray,
        startAngle = 0f,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(
            x = secondCircleX - circleRadius,
            y = centerY - circleRadius
        ),
        size = Size(circleRadius * 2, circleRadius * 2)
    )
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawMouth(
    centerX: Float,
    centerY: Float,
    circleRadius: Float,
    mouthCurve: Float
) {
    val mouthY = centerY + circleRadius
    val mouthWidth = 100f
    val mouthThickness = 80f

    val path = Path().apply {
        val startX = centerX - mouthWidth / 2f
        val endX = centerX + mouthWidth / 2f
        val controlX = centerX
        val controlY = mouthY - mouthCurve

        moveTo(startX, mouthY)
        quadraticBezierTo(
            x1 = controlX, y1 = controlY,
            x2 = endX, y2 = mouthY
        )
    }

    drawPath(
        path = path,
        color = EmotionColors.DarkGray,
        style = Stroke(
            width = mouthThickness,
            cap = StrokeCap.Round
        )
    )
}