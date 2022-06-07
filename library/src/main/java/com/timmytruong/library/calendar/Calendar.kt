package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.timmytruong.library.calendar.selection.DateSelection
import com.timmytruong.library.calendar.selection.MultipleDaySelectionCalendar
import com.timmytruong.library.calendar.selection.RangeSelectionCalendar
import com.timmytruong.library.calendar.selection.SingleDaySelectionCalendar
import com.timmytruong.library.core.Title
import com.timmytruong.library.core.TitleData
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
    titleData: TitleData? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            data = titleData,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )
        DayOfWeekHeader(startingDay = startingDay)
        when (dateSelection) {
            is DateSelection.SingleDay -> SingleDaySelectionCalendar(yearMonth, startingDay, dateSelection)
            is DateSelection.MultipleDay -> MultipleDaySelectionCalendar(yearMonth, startingDay, dateSelection)
            is DateSelection.Range -> RangeSelectionCalendar(yearMonth, startingDay, dateSelection)
            else -> StaticCalendar(yearMonth, startingDay)
        }
    }
}