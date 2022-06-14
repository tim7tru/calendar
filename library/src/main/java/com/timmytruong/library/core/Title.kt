package com.timmytruong.library.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun Title(data: CalendarTextData?, text: String) {
    Text(
        text = text,
        modifier = data?.modifier ?: Modifier.DEFAULT,
        fontSize = data?.fontSize ?: DEFAULT_FONT_SIZE,
        textAlign = data?.textAlign ?: TextAlign.Center,
        color = data?.textColor ?: Color.White
    )
}

private val Modifier.DEFAULT get() = padding(8.dp)

private val DEFAULT_FONT_SIZE get() = 24.sp