package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.extension.generateDays
import com.timmytruong.library.extension.isIn

@ExperimentalFoundationApi
@Composable
fun StaticCalendar(properties: CalendarProperties) {
    with(properties) {
        val days = remember { generateDays() }
        LazyVerticalGrid(
            cells = GridCells.Fixed(DAYS_IN_WEEK),
            content = {
                items(days) { day ->
                    Day(
                        dayData = day?.let { date ->
                            DayData.StaticDayData(
                                date = date,
                                textData = if (date isIn yearMonth) onMonthDayData else offMonthDayData
                            )
                        } ?: DayData.EmptyDay()
                    )
                }
            }
        )
    }
}
