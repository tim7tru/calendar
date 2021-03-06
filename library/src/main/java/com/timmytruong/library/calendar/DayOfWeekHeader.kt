package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.timmytruong.library.core.CalendarTextData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.reverseWithIndices
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@ExperimentalFoundationApi
@Composable
fun DayOfWeekHeader(
    startingDay: DayOfWeek,
    textData: CalendarTextData?
) {
    val daysOfWeek = remember(startingDay) { getDaysOfWeek(startingDay).toList() }
    DayGrid(
        gridItems = daysOfWeek,
        composable = { dayOfWeek ->
            dayOfWeek?.let {
                Text(
                    text = it.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                    modifier = textData?.modifier ?: Modifier,
                    fontSize = textData?.fontSize ?: 24.sp,
                    textAlign = textData?.textAlign ?: TextAlign.Center,
                    fontWeight = textData?.fontWeight ?: FontWeight.Bold,
                    color = textData?.textColor ?: Color.White,
                )
            }
        }
    )
}

private fun getDaysOfWeek(startingDay: DayOfWeek) = DayOfWeek.values().apply {
    val pivot = startingDay.ordinal
    reverse()
    reverseWithIndices(0, size - pivot - 1)
    reverseWithIndices(size - pivot, size - 1)
}