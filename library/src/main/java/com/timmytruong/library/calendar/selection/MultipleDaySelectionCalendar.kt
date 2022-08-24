package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    calendarState: CalendarState<List<LocalDate>>,
    dayTextData: DayTextData
) {
    DayGrid(gridItems = days) {
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
}

class MultiSelectionState(
    initial: List<LocalDate>? = null,
    onDateSelected: DaySelection? = null,
    private val onStateUpdated: DateStateUpdate<List<LocalDate>>? = null
): CalendarState<List<LocalDate>>(CalendarSelection.MULTI_DAY, onDateSelected) {

    override val selected = initial?.let { mutableStateOf(it) }

    override fun isSelected(date: LocalDate): Boolean {
        return selected?.value?.contains(date) == true
    }

    override fun onDateSelected(date: LocalDate) {
        super.onDateSelected(date)
        if (selected?.value?.contains(date) == true) {
            selected.value = selected.value.toMutableList().apply { remove(date) }
        } else {
            selected?.value?.toMutableList()?.apply {
                add(date)
                selected.value = this
            }
        }
        selected?.value?.let { onStateUpdated?.invoke(it) }
    }
}


@Composable
fun rememberMultiSelectionState(
    initial: List<LocalDate>?,
    onDateSelected: DaySelection?,
    onStateUpdated: DateStateUpdate<List<LocalDate>>?
): MultiSelectionState = remember(initial) {
    MultiSelectionState(initial, onDateSelected, onStateUpdated)
}