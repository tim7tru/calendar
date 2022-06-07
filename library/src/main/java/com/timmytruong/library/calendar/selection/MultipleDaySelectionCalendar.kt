package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.timmytruong.library.calendar.DAYS_IN_WEEK
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import com.timmytruong.library.extension.toDays
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
fun MultipleDaySelectionCalendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    dateSelection: DateSelection.MultipleDay
) {
    val selectedDates = remember {
        mutableStateListOf<LocalDate>().apply { dateSelection.initial?.let { addAll(it) } }
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(yearMonth.toDays(startingDay)) { item ->
                item?.let { date ->
                    SimpleDayTile(
                        data = DayData(
                            date = date,
                            dayClicks = {
                                with(selectedDates) {
                                    if (contains(date)) remove(date)
                                    else add(date)
                                }
                            },
                            isSelected = selectedDates.contains(date)
                        )
                    )
                } ?: EmptyDayTile()
            }
        }
    )
}