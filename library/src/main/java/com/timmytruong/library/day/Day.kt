package com.timmytruong.library.day

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate

internal data class DayData(
    val date: LocalDate,
    val isSelected: Boolean = false,
    val dayClicks: (() -> Unit)? = null

)

@Composable
internal fun EmptyDayTile() {
    Text(
        text = " ",
        color = Color.White,
        modifier = Modifier.padding(all = 8.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
internal fun SimpleDayTile(data: DayData) {
    var modifier = Modifier
        .padding(all = 8.dp)
        .drawBehind {
            if (data.isSelected) drawCircle(color = Color.Red)
            else drawCircle(color = Color.Transparent)
        }

    if (data.dayClicks != null) {
        modifier = Modifier.clickable { data.dayClicks.invoke() }.then(modifier)
    }

    with(data) {
        Text(
            text = "${date.dayOfMonth}",
            color = Color.White,
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}


