package com.timmytruong.library.day

import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.timmytruong.library.core.CalendarTextData
import java.time.LocalDate

internal sealed class DayData {

    internal data class EmptyDay(val modifier: Modifier = Modifier) : DayData()

    internal data class StaticDayData(
        val date: LocalDate,
        val textData: CalendarTextData? = null,
        val modifier: Modifier = Modifier
    ): DayData()

    internal data class SelectableDayData(
        val date: LocalDate,
        val isSelected: Boolean = false,
        val dayClicks: () -> Unit,
        val textData: CalendarTextData? = null
    ): DayData()
}

@Composable
internal fun Day(dayData: DayData) {
    when (dayData) {
        is DayData.StaticDayData -> StaticDay(dayData)
        is DayData.SelectableDayData -> SelectableDay(dayData)
        is DayData.EmptyDay -> EmptyDay(dayData)
    }
}

@Composable
private fun StaticDay(dayData: DayData.StaticDayData) {
    with(dayData) {
        Text(
            text = "${date.dayOfMonth}",
            color = textData?.textColor ?: Color.White,
            modifier = textData?.modifier ?: modifier,
            textAlign = textData?.textAlign ?: TextAlign.Center,
            fontSize = textData?.fontSize ?: 12.sp
        )
    }
}

@Composable
private fun EmptyDay(dayData: DayData.EmptyDay) {
    Text(text = " ", modifier = dayData.modifier)
}

@Composable
private fun SelectableDay(dayData: DayData.SelectableDayData) {
    with(dayData) {
        val modifier = (textData?.modifier ?: Modifier).then(
            Modifier.selectable(
                selected = dayData.isSelected,
                onClick = { dayData.dayClicks() }
            ).drawBehind {
                if (dayData.isSelected) drawCircle(color = Color.Red)
                else drawCircle(color = Color.Transparent)
            }
        )

        Text(
            text = "${date.dayOfMonth}",
            color = textData?.textColor ?: Color.White,
            modifier = modifier,
            textAlign = textData?.textAlign ?: TextAlign.Center,
            fontSize = textData?.fontSize ?: 12.sp
        )
    }
}
