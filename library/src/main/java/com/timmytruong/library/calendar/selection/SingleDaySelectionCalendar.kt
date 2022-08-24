package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import com.timmytruong.library.calendar.DayTextData
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
internal fun SingleDaySelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    calendarState: CalendarState<LocalDate>,
    dayTextData: DayTextData
) {
    DayGrid(gridItems = days) {
        val data = it?.let { date ->
            DayData.SelectableDayData(
                date = date,
                dayClicks = { calendarState.onDateSelected(date) },
                isSelected = calendarState.isSelected(date),
                textData = dayTextData.resolve(date isIn currentMonth)
            )
        } ?: DayData.EmptyDay()
        Day(data)
    }
}

class SingleSelectionState(
    initial: LocalDate? = null,
    onDateSelected: DaySelection? = null,
    private val onStateUpdated: DateStateUpdate<LocalDate>? = null
): CalendarState<LocalDate>(CalendarSelection.SINGLE_DAY, onDateSelected) {

    override val selected = initial?.let { mutableStateOf(it) }

    override fun isSelected(date: LocalDate): Boolean = selected?.value == date

    override fun onDateSelected(date: LocalDate) {
        super.onDateSelected(date)
        selected?.value = date
        onStateUpdated?.invoke(date)
    }
}

@Composable
fun rememberSingleSelectionState(
    initial: LocalDate?,
    onDateSelected: DaySelection?,
    onStateUpdated: DateStateUpdate<LocalDate>?
): SingleSelectionState = remember(initial) {
    SingleSelectionState(initial, onDateSelected, onStateUpdated)
}