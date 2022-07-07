package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.timmytruong.library.calendar.DayTextData
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
internal fun MultipleDaySelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    dateSelection: DateSelection.MultipleDay,
    dayTextData: DayTextData
) {
    val selectedDates = remember {
        mutableStateListOf<LocalDate>().apply { dateSelection.initial?.let { addAll(it) } }
    }
    DayGrid(gridItems = days) {
        Day(
            dayData = it?.let { date ->
                DayData.SelectableDayData(
                    date = date,
                    dayClicks = {
                        selectedDates.onClick(date)
                        dateSelection.onDaySelected(date)
                        dateSelection.onStateUpdated(selectedDates)
                    },
                    isSelected = selectedDates.contains(date),
                    textData = dayTextData.resolve(date isIn currentMonth)
                )
            } ?: DayData.EmptyDay()
        )
    }
}

private fun SnapshotStateList<LocalDate>.onClick(date: LocalDate) {
    if (contains(date)) remove(date)
    else add(date)
}