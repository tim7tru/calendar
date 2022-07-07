package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
internal fun StaticCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    dayTextData: DayTextData
) {
    DayGrid(
        gridItems = days,
        composable = {
            val data = it?.run {
                DayData.StaticDayData(
                    date = this,
                    textData = dayTextData.resolve(this isIn currentMonth)
                )
            } ?: DayData.EmptyDay()
            Day(data)
        }
    )
}
