package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.timmytruong.library.calendar.CalendarProperties
import com.timmytruong.library.calendar.DAYS_IN_WEEK
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.extension.generateDays
import com.timmytruong.library.extension.isIn
import java.time.LocalDate

@ExperimentalFoundationApi
@Composable
internal fun MultipleDaySelectionCalendar(
    dateSelection: DateSelection.MultipleDay,
    properties: CalendarProperties
) {
    with(properties) {
        val selectedDates = remember {
            mutableStateListOf<LocalDate>().apply { dateSelection.initial?.let { addAll(it) } }
        }
        val days = remember { generateDays() }
        LazyVerticalGrid(cells = GridCells.Fixed(DAYS_IN_WEEK), content = {
            items(days) { day ->
                Day(
                    dayData = day?.let { date ->
                        DayData.SelectableDayData(
                            date = date,
                            dayClicks = { selectedDates.onClick(date) },
                            isSelected = selectedDates.contains(date),
                            textData = if (date isIn yearMonth) onMonthDayData else offMonthDayData
                        )
                    } ?: DayData.EmptyDay()
                )
            }
        })
    }
}

private fun SnapshotStateList<LocalDate>.onClick(date: LocalDate) {
    if (contains(date)) remove(date)
    else add(date)
}