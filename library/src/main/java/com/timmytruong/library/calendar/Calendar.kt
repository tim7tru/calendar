package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.timmytruong.library.calendar.selection.*
import com.timmytruong.library.calendar.selection.MultipleDaySelectionCalendar
import com.timmytruong.library.calendar.selection.RangeSelectionCalendar
import com.timmytruong.library.calendar.selection.SingleDaySelectionCalendar
import com.timmytruong.library.core.CalendarTextData
import com.timmytruong.library.core.Title
import com.timmytruong.library.extension.months
import com.timmytruong.library.extension.toDays
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

const val DAYS_IN_WEEK = 7

@ExperimentalFoundationApi
@Composable
fun Calendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    calendarState: CalendarState<*>? = null,
    hasOffMonthDays: Boolean = true,
    titleData: CalendarTextData? = null,
    headerData: CalendarTextData? = null,
    onMonthDayData: CalendarTextData? = null,
    offMonthDayData: CalendarTextData? = null
) {
    val dayTextData = remember {
        DayTextData(
            onMonthDayData = onMonthDayData,
            offMonthDayData = offMonthDayData
        )
    }

    val title = remember {
        "${yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${yearMonth.year}"
    }

    val days = remember { yearMonth.toDays(startingDay, hasOffMonthDays) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(data = titleData, text = title)
        DayOfWeekHeader(startingDay = startingDay, textData = headerData)

        when (calendarState?.selectionType) {
            CalendarSelection.SINGLE_DAY -> SingleDaySelectionCalendar(days, yearMonth,
                                                                       calendarState as CalendarState<LocalDate>, dayTextData)
            CalendarSelection.MULTI_DAY-> MultipleDaySelectionCalendar(days, yearMonth,
                                                                       calendarState as CalendarState<List<LocalDate>>, dayTextData)
            CalendarSelection.RANGE -> RangeSelectionCalendar(days, yearMonth,
                                                              calendarState as CalendarState<Pair<LocalDate, LocalDate>>, dayTextData)
            else -> StaticCalendar(days, yearMonth, dayTextData)
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HorizontalCalendarRange(
    range: Pair<YearMonth, YearMonth>,
    startingDay: DayOfWeek,
    startingMonth: YearMonth? = null,
    calendarState: CalendarState<*>? = null,
    hasOffMonthDays: Boolean = true,
    titleData: CalendarTextData? = null,
    headerData: CalendarTextData? = null,
    onMonthDayData: CalendarTextData? = null,
    offMonthDayData: CalendarTextData? = null
) {
    val months = remember { range.months }
    val days = remember { months.map { it.toDays(startingDay, hasOffMonthDays) } }
    val pagerState = rememberPagerState(months.indexOf(startingMonth))
    val dayTextData = remember {
        DayTextData(
            onMonthDayData = onMonthDayData,
            offMonthDayData = offMonthDayData
        )
    }

    HorizontalPager(
        verticalAlignment = Alignment.Top,
        count = months.size,
        state = pagerState
    ) { page ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val title = "${months[page].month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${months[page].year}"
            Title(data = titleData, text = title)
            DayOfWeekHeader(startingDay = startingDay, textData = headerData)

            when (calendarState?.selectionType) {
                CalendarSelection.SINGLE_DAY -> SingleDaySelectionCalendar(days[page], months[page],
                                                                           calendarState as CalendarState<LocalDate>, dayTextData)
                CalendarSelection.MULTI_DAY-> MultipleDaySelectionCalendar(days[page], months[page],
                                                                           calendarState as CalendarState<List<LocalDate>>, dayTextData)
                CalendarSelection.RANGE -> RangeSelectionCalendar(days[page], months[page],
                                                                  calendarState as CalendarState<Pair<LocalDate, LocalDate>>, dayTextData)
                else -> StaticCalendar(days[page], months[page], dayTextData)
            }
        }
    }
}
