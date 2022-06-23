package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.timmytruong.library.calendar.DayTextData
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.*
import com.timmytruong.library.extension.isBetween
import com.timmytruong.library.extension.isSelected
import com.timmytruong.library.extension.isValidRange
import java.time.LocalDate
import java.time.YearMonth

private val NO_RANGE = LocalDate.MAX to LocalDate.MIN

@ExperimentalFoundationApi
@Composable
internal fun RangeSelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    dateSelection: DateSelection.Range,
    dayTextData: DayTextData
) {
    val selectedDateRange = remember {
        mutableStateOf(
            if (dateSelection.initial?.isValidRange() == true) dateSelection.initial
            else NO_RANGE
        )
    }

    DayGrid(
        gridItems = days,
        composable = {
            Day(
                dayData = it?.let { date ->
                    DayData.SelectableDayData(
                        date = date,
                        dayClicks = { selectedDateRange.onClick(date) },
                        isSelected = date.isSelected(selectedDateRange.value),
                        textData = dayTextData.resolve(date isIn currentMonth)
                    )
                } ?: DayData.EmptyDay()
            )
        }
    )
}

private fun MutableState<Pair<LocalDate, LocalDate>>.onClick(date: LocalDate) {
    value = with(value) {
        when {
            this == NO_RANGE || date.isBefore(first) -> date to LocalDate.MIN
            date.isEqual(first) -> NO_RANGE
            date.isBetween(this) || date.isAfterOrEqual(second) -> first to date
            else -> value
        }
    }
}
