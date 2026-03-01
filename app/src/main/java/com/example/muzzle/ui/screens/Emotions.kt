package com.example.muzzle.ui.screens
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import com.example.muzzle.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class Mode {
    HAPPY,
    CHILL,
    ANGRY
}
@Composable
fun Emotions() {

    val bellota = FontFamily(
        Font(R.font.bellota, FontWeight.Normal)
    )

    var selectMode by remember { mutableStateOf(Mode.CHILL) }

    val boxVisible = remember { mutableStateOf(true) }

    val transition = updateTransition(
        targetState = selectMode,
        label = "emotions_transition"
    )

    val circleRadiusDp by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 500)
        },
        label = "radius"
    ) { mode ->
        when (mode) {
            Mode.ANGRY -> 60.dp
            Mode.CHILL -> 60.dp
            Mode.HAPPY -> 70.dp
        }
    }

    val sweepAngle by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500)
        },
        label = "sweepAngle"
    ) { mode ->
        when (mode) {
            Mode.ANGRY -> 180f
            Mode.CHILL -> 360f
            Mode.HAPPY -> 360f
        }
    }

    val browAngle by animateFloatAsState(
        targetValue = when (selectMode) {
            Mode.HAPPY -> -30f
            Mode.CHILL -> 0f
            Mode.ANGRY -> 30f
        },
        label = "browAngle"
    )

    val mouthCurve by animateFloatAsState(
        targetValue = when (selectMode) {
            Mode.HAPPY -> -50f
            Mode.CHILL -> 0f
            Mode.ANGRY -> 50f
        },
        label = "mouthCurve"
    )

    var blinkingMode by remember { mutableStateOf<Mode?>(null) }
    val infiniteTransition = rememberInfiniteTransition(label = "blink_transition")
    val blinkAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink_animation"
    )



    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        when (selectMode) {
                            Mode.HAPPY -> Color(0xFF4C6851)
                            Mode.ANGRY -> Color(0xFFB83A2E)
                            Mode.CHILL -> Color(0xFFF9AC45)
                        }
                    )
            )

        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "angry",
            fontSize = 20.sp,
            color = if (blinkingMode == Mode.ANGRY)
                Color(0x20000000).copy(alpha = blinkAlpha)
            else
                Color(0x50000000),
            fontFamily = bellota
        )

        Text(
            text = "chill",
            fontSize = 20.sp,
            color = if (blinkingMode == Mode.CHILL)
                Color(0x20000000).copy(alpha = blinkAlpha)
            else
                Color(0x50000000),
            fontFamily = bellota
        )

        Text(
            text = "happy",
            fontSize = 20.sp,
            color = if (blinkingMode == Mode.HAPPY)
                Color(0x20000000).copy(alpha = blinkAlpha)
            else
                Color(0x50000000),
            fontFamily = bellota
        )
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        IconButton(
            onClick = {
                selectMode = Mode.ANGRY
                blinkingMode = Mode.ANGRY
            },
            modifier = Modifier.size(80.dp)
        ) {
            Image(
                painter = painterResource(
                    id = if (selectMode == Mode.ANGRY)
                        R.drawable.angry_active
                    else
                        R.drawable.angry
                ),
                contentDescription = "happy",
                modifier = Modifier.size(60.dp)
            )
        }



        IconButton(
            onClick = {
                selectMode = Mode.CHILL
                blinkingMode = Mode.CHILL
            },
            modifier = Modifier.size(80.dp)
        ) {
            Image(
                painter = painterResource(
                    id = if (selectMode == Mode.CHILL)
                        R.drawable.chill_active
                    else
                        R.drawable.chill
                ),
                contentDescription = "angry",
                modifier = Modifier.size(60.dp)
            )

        }
        IconButton(
            onClick = {
                selectMode = Mode.HAPPY
                blinkingMode = Mode.HAPPY
            },
            modifier = Modifier.size(80.dp)
        ) {
            Image(
                painter = painterResource(
                    id = if (selectMode == Mode.HAPPY)
                        R.drawable.happy_active
                    else
                        R.drawable.happy
                ),
                contentDescription = "chill",
                modifier = Modifier.size(60.dp)
            )
        }

    }




    AnimatedVisibility(
        visible = boxVisible.value,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Box(
            modifier = Modifier
                .drawBehind {
                    val canvasWidth = size.width
                    val circleRadius = circleRadiusDp.toPx()
                    val centerX = canvasWidth / 2f
                    val eyesDistance = canvasWidth * 0.4f
                    val firstCircleX = centerX - eyesDistance / 2f
                    val secondCircleX = centerX + eyesDistance / 2f
                    val centerY = size.height

                    val browWidth = 250f
                    val browHeight = 100f
                    val browOffset = 50f

                    rotate(
                        degrees = browAngle,
                        pivot = Offset(firstCircleX, centerY - circleRadius)
                    ) {
                        drawRoundRect(
                            color = Color(0xFF1E1E1E),
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
                            color = Color(0xFF1E1E1E),
                            topLeft = Offset(
                                x = secondCircleX - browWidth / 2f,
                                y = centerY - circleRadius - browOffset - browHeight
                            ),
                            size = Size(browWidth, browHeight),
                            cornerRadius = CornerRadius(browHeight / 2f, browHeight / 2f)
                        )
                    }

                    drawArc(
                        color = Color(0xFF1E1E1E),
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
                        color = Color(0xFF1E1E1E),
                        startAngle = 0f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(
                            x = secondCircleX - circleRadius,
                            y = centerY - circleRadius
                        ),
                        size = Size(circleRadius * 2, circleRadius * 2)
                    )

                    val mouthY = centerY + circleRadius
                    val mouthX = centerX
                    val mouthWidth = 100f
                    val mouthThickness = 80f

                    val path = Path().apply {
                        val startX = mouthX - mouthWidth / 2f
                        val endX = mouthX + mouthWidth / 2f
                        val controlX = mouthX
                        val controlY = mouthY - mouthCurve

                        moveTo(startX, mouthY)
                        quadraticBezierTo(
                            x1 = controlX, y1 = controlY,
                            x2 = endX, y2 = mouthY
                        )
                    }

                    drawPath(
                        path = path,
                        color = Color(0xFF1E1E1E),
                        style = Stroke(
                            width = mouthThickness,
                            cap = StrokeCap.Round
                        )
                    )

                }
                .animateContentSize()
                .fillMaxWidth()
                .height(450.dp)


        )



    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 700.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            { boxVisible.value = true },
            enabled = !boxVisible.value,
            modifier = Modifier.size(120.dp)
        ) {
            Image(
                painter = painterResource(
                R.drawable.muz_on),
                contentDescription = "muz on"
            )
        }
        IconButton(
            { boxVisible.value = false },
            enabled = boxVisible.value,
            modifier = Modifier.size(120.dp)
        ) {
                Image(
                    painter = painterResource(
                        R.drawable.muz_off),
                    contentDescription = "muz off"
                )
            }
        }
    }




