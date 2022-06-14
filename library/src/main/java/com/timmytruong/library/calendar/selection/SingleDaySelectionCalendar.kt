package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.timmytruong.library.calendar.CalendarProperties
import com.timmytruong.library.calendar.DAYS_IN_WEEK
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
fun SingleDaySelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    dateSelection: DateSelection.SingleDay,
    properties: CalendarProperties
) {
    with(properties) {
        val selectedDate = remember { mutableStateOf(dateSelection.initial) }

        LazyVerticalGrid(
            cells = GridCells.Fixed(DAYS_IN_WEEK),
            content = {
                items(days) { day ->
                    val data = day?.let { date ->
                        DayData.SelectableDayData(
                            date = date,
                            dayClicks = { selectedDate.value = date },
                            isSelected = date.isEqual(selectedDate.value),
                            textData = if (date isIn currentMonth) onMonthDayData else offMonthDayData
                        )
                    } ?: DayData.EmptyDay()
                    Day(data)
                }
            }
        )
    }
}