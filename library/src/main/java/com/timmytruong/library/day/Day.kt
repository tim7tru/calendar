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
import androidx.compose.ui.unit.sp
import com.timmytruong.library.core.ComposableTextData
import java.time.LocalDate

internal sealed class DayData {

    internal data class EmptyDay(val modifier: Modifier = Modifier) : DayData()

    internal data class StaticDayData(
        val date: LocalDate,
        val textData: ComposableTextData? = null,
        val modifier: Modifier = Modifier
    ): DayData()

    internal data class SelectableDayData(
        val date: LocalDate,
        val isSelected: Boolean = false,
        val dayClicks: (() -> Unit)? = null,
        val textData: ComposableTextData? = null
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
            modifier = modifier,
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
    var modifier: Modifier = dayData.textData?.modifier ?: Modifier

    if (modifier == Modifier) {
        modifier = modifier.then(
            Modifier
                .padding(all = 8.dp)
                .drawBehind {
                    if (dayData.isSelected) drawCircle(color = Color.Red)
                    else drawCircle(color = Color.Transparent)
                }
        )
    }

    with(dayData) {
        dayClicks?.let {
            modifier = modifier.then(Modifier.clickable { it.invoke() })
        }

        Text(
            text = "${date.dayOfMonth}",
            color = textData?.textColor ?: Color.White,
            modifier = modifier,
            textAlign = textData?.textAlign ?: TextAlign.Center,
            fontSize = textData?.fontSize ?: 12.sp
        )
    }
}
