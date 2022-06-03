package com.timmytruong.library.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TitleData(
    val modifier: Modifier = Modifier.DEFAULT,
    val fontSize: TextUnit = DEFAULT_FONT_SIZE
)

@Composable
internal fun Title(data: TitleData?, text: String) {
    Text(
        text = text,
        modifier = data?.modifier ?: Modifier.DEFAULT,
        fontSize = data?.fontSize ?: DEFAULT_FONT_SIZE
    )
}

private val Modifier.DEFAULT get() = padding(8.dp)

private val DEFAULT_FONT_SIZE get() = 24.sp