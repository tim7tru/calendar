package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.timmytruong.library.calendar.selection.DateSelection
import com.timmytruong.library.calendar.selection.MultipleDaySelectionCalendar
import com.timmytruong.library.calendar.selection.RangeSelectionCalendar
import com.timmytruong.library.calendar.selection.SingleDaySelectionCalendar
import com.timmytruong.library.core.CalendarTextData
import com.timmytruong.library.core.Title
import com.timmytruong.library.extension.toDays
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

const val DAYS_IN_WEEK = 7

@ExperimentalFoundationApi
@Composable
fun Calendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    dateSelection: DateSelection<*>? = null,
    hasOffMonthDays: Boolean = true,
    titleData: CalendarTextData? = null,
    headerData: CalendarTextData? = null,
    onMonthDayData: CalendarTextData? = null,
    offMonthDayData: CalendarTextData? = null
) {
    val dayTextData = DayTextData(
        onMonthDayData = onMonthDayData,
        offMonthDayData = offMonthDayData
    )
    val days = yearMonth.toDays(startingDay, hasOffMonthDays)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            data = titleData,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )

        DayOfWeekHeader(
            startingDay = startingDay,
            textData = headerData
        )

        when (dateSelection) {
            is DateSelection.SingleDay -> SingleDaySelectionCalendar(days, yearMonth, dateSelection, dayTextData)
            is DateSelection.MultipleDay -> MultipleDaySelectionCalendar(days, yearMonth, dateSelection, dayTextData)
            is DateSelection.Range -> RangeSelectionCalendar(days, yearMonth, dateSelection, dayTextData)
            else -> StaticCalendar(days, yearMonth, dayTextData)
        }
    }
}
