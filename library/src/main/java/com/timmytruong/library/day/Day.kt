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

data class DayData(
    val date: LocalDate? = null,
    val isSelected: Boolean = false
)

@Composable
fun EmptyDayTile() {
    Text(
        text = " ",
        color = Color.White,
        modifier = Modifier.padding(all = 8.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun SimpleDayTile(data: DayData, onDaySelected: ((LocalDate) -> Unit)? = null) {
    Text(
        text = "${data.date?.dayOfMonth}",
        color = Color.White,
        modifier = Modifier
            .padding(all = 8.dp)
            .drawBehind {
                if (data.isSelected) drawCircle(color = Color.Red)
                else drawCircle(color = Color.Transparent)
            }
            .clickable { data.date?.let { onDaySelected?.invoke(it) } },
        textAlign = TextAlign.Center
    )
}
