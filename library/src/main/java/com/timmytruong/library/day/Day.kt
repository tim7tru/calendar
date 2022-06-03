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
import com.timmytruong.library.calendar.DateSelection
import java.time.LocalDate

data class DayData(
    val date: LocalDate? = null,
    val selection: DateSelection.SingleDay? = null
) {
    fun isDateSelected() = selection?.selectedDate?.value == date
}

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
fun SimpleDayTile(data: DayData) {
    with(data) {
        Text(
            text = "${date?.dayOfMonth}",
            color = Color.White,
            modifier = Modifier
                .padding(all = 8.dp)
                .drawBehind {
                    if (data.isDateSelected()) drawCircle(color = Color.Red)
                    else drawCircle(color = Color.Transparent)
                }
                .clickable { date?.let { selection?.onDaySelected?.invoke(it) } },
            textAlign = TextAlign.Center
        )
    }
}
