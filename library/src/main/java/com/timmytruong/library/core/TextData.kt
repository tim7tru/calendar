package com.timmytruong.library.core

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

data class CalendarTextData(
    val modifier: Modifier = Modifier,
    val textColor: Color? = null,
    val fontSize: TextUnit? = null,
    val textAlign: TextAlign? = null,
    val fontWeight: FontWeight? = null
)
