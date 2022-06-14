package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

internal const val DAYS_IN_WEEK = 7

@ExperimentalFoundationApi
@Composable
fun Calendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    dateSelection: DateSelection<*>? = null,
    hasOffMonthDays: Boolean = true,
    titleData: CalendarTextData? = null,
    onMonthDayData: CalendarTextData? = null,
    offMonthDayData: CalendarTextData? = null
) {
    val properties = CalendarProperties(
        titleData = titleData,
        onMonthDayData = onMonthDayData,
        offMonthDayData = offMonthDayData
    )
    val days = remember { yearMonth.toDays(startingDay, hasOffMonthDays) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            data = titleData,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )

        DayOfWeekHeader(startingDay = startingDay)

        when (dateSelection) {
            is DateSelection.SingleDay -> SingleDaySelectionCalendar(days, yearMonth, dateSelection, properties)
            is DateSelection.MultipleDay -> MultipleDaySelectionCalendar(days, yearMonth, dateSelection, properties)
            is DateSelection.Range -> RangeSelectionCalendar(days, yearMonth, dateSelection, properties)
            else -> StaticCalendar(days, yearMonth, properties)
        }
    }
}
