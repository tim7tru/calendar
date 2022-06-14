package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
fun StaticCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    properties: CalendarProperties
) {
    with(properties) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(DAYS_IN_WEEK),
            content = {
                items(days) { day ->
                    Day(
                        dayData = day?.let { date ->
                            DayData.StaticDayData(
                                date = date,
                                textData = if (date isIn currentMonth) onMonthDayData else offMonthDayData
                            )
                        } ?: DayData.EmptyDay()
                    )
                }
            }
        )
    }
}
