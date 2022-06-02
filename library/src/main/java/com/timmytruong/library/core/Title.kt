package com.timmytruong.library.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun Title(modifier: Modifier, text: String) {
    Text(modifier = modifier, text = text, fontSize = 24.sp)
}