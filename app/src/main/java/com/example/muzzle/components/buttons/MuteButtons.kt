package com.example.muzzle.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.muzzle.R

@Composable
fun MuteButtons(
    isVisible: Boolean,
    onMuteOn: () -> Unit,
    onMuteOff: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 700.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = onMuteOn,
            enabled = !isVisible,
            modifier = Modifier.size(120.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.muz_on),
                contentDescription = "muz on"
            )
        }
        IconButton(
            onClick = onMuteOff,
            enabled = isVisible,
            modifier = Modifier.size(120.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.muz_off),
                contentDescription = "muz off"
            )
        }
    }
}