package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.timmytruong.library.calendar.*
import com.timmytruong.library.calendar.DayTextData
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.*
import java.time.LocalDate
import java.time.YearMonth

private val NO_RANGE = LocalDate.MAX to LocalDate.MIN

@ExperimentalFoundationApi
@Composable
internal fun RangeSelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    calendarState: CalendarState<Pair<LocalDate, LocalDate>>,
    dayTextData: DayTextData
) {
    DayGrid(
        gridItems = days,
        composable = {
            Day(
                dayData = it?.let { date ->
                    DayData.SelectableDayData(
                        date = date,
                        dayClicks = { calendarState.onDateSelected(date) },
                        isSelected = calendarState.isSelected(date),
                        textData = dayTextData.resolve(date isIn currentMonth)
                    )
                } ?: DayData.EmptyDay()
            )
        }
    )
}

class RangeSelectionState(
    initial: Pair<LocalDate, LocalDate>? = null,
    onDateSelected: DaySelection? = null,
    private val onStateUpdated: DateStateUpdate<Pair<LocalDate, LocalDate>>? = null
): CalendarState<Pair<LocalDate, LocalDate>>(CalendarSelection.RANGE, onDateSelected) {

    override val selected = initial?.let { mutableStateOf(it) }

    override fun isSelected(date: LocalDate): Boolean {
        return selected?.value?.let { date.isSelected(it) } ?: false
    }

    override fun onDateSelected(date: LocalDate) {
        super.onDateSelected(date)
        selected?.onClick(date)
        selected?.value?.let { onStateUpdated?.invoke(it) }
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
}

@Composable
fun rememberRangeSelectionState(
    initial: Pair<LocalDate, LocalDate>?,
    onDateSelected: DaySelection?,
    onStateUpdated: DateStateUpdate<Pair<LocalDate, LocalDate>>?
): RangeSelectionState = remember(initial) {
    RangeSelectionState(initial, onDateSelected, onStateUpdated)
}
