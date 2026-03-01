package com.example.muzzle.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.muzzle.R
import com.example.muzzle.model.Mode

@Composable
fun EmotionButton(
    mode: Mode,
    currentMode: Mode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRes = when (mode) {
        Mode.ANGRY -> if (currentMode == Mode.ANGRY) R.drawable.angry_active else R.drawable.angry
        Mode.CHILL -> if (currentMode == Mode.CHILL) R.drawable.chill_active else R.drawable.chill
        Mode.HAPPY -> if (currentMode == Mode.HAPPY) R.drawable.happy_active else R.drawable.happy
    }

    IconButton(
        onClick = onClick,
        modifier = modifier.size(80.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = mode.name.lowercase(),
            modifier = Modifier.size(60.dp)
        )
    }
}