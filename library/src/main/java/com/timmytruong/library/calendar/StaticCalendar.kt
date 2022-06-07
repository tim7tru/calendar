package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import com.timmytruong.library.extension.toDays
import java.time.DayOfWeek
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
fun StaticCalendar(yearMonth: YearMonth, startingDay: DayOfWeek) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(yearMonth.toDays(startingDay)) { item ->
                item?.let { SimpleDayTile(data = DayData(date = it)) } ?: EmptyDayTile()
            }
        }
    )
}