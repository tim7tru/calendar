package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.timmytruong.library.calendar.DAYS_IN_WEEK
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import com.timmytruong.library.extension.*
import com.timmytruong.library.extension.isBetween
import com.timmytruong.library.extension.isSelected
import com.timmytruong.library.extension.isValidRange
import com.timmytruong.library.extension.toDayData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

private val NO_RANGE = LocalDate.MAX to LocalDate.MIN

@ExperimentalFoundationApi
@Composable
fun RangeSelectionCalendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek,
    dateSelection: DateSelection.Range
) {
    val selectedDateRange = remember {
        mutableStateOf(
            if (dateSelection.initial?.isValidRange() == true) dateSelection.initial
            else NO_RANGE
        )
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(yearMonth.toDayData(startingDay)) { item ->
                item?.let { date ->
                    SimpleDayTile(
                        data = DayData(
                            date = date,
                            dayClicks = {
                                selectedDateRange.value = with(selectedDateRange.value) {
                                   when {
                                       this == NO_RANGE || date.isBefore(first)  -> date to LocalDate.MIN
                                       date.isEqual(first) -> NO_RANGE
                                       date.isBetween(this) || date.isAfterOrEqual(second) -> first to date
                                       else -> selectedDateRange.value
                                   }
                                }
                            },
                            isSelected = date.isSelected(selectedDateRange.value)
                        )
                    )
                } ?: EmptyDayTile()
            }
        }
    )
}

