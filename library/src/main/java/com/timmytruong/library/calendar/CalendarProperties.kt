package com.timmytruong.library.calendar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.timmytruong.library.core.CalendarTextData

internal data class DayTextData(
    val onMonthDayData: CalendarTextData?,
    val offMonthDayData: CalendarTextData?
) {
    internal companion object {
        private val DEFAULT_DAY_TEXT_DATA = CalendarTextData(
            textColor = Color.Red,
            fontSize = 8.sp,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Normal
        )
    }

    internal fun resolve(isOffMonth: Boolean) = if (isOffMonth) offMonth else onMonth

    private val offMonth get() = offMonthDayData ?: onMonthDayData ?: DEFAULT_DAY_TEXT_DATA

    private val onMonth get() = onMonthDayData ?: DEFAULT_DAY_TEXT_DATA
}