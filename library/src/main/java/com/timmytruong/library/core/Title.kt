package com.timmytruong.library.core

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String) {
    Text(text = text, fontSize = 24.sp)
}