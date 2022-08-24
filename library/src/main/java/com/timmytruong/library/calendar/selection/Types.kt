package com.timmytruong.library.calendar.selection

import androidx.compose.runtime.MutableState
import java.time.LocalDate

enum class CalendarSelection{
    SINGLE_DAY,
    MULTI_DAY,
    RANGE
}

internal typealias DaySelection = (LocalDate) -> Unit
internal typealias DateStateUpdate<T> = (T) -> Unit

abstract class CalendarState<T>(
    val selectionType: CalendarSelection,
    private val onDateSelected: DaySelection?
) {

    abstract val selected: MutableState<T>?
    abstract fun isSelected(date: LocalDate): Boolean
    open fun onDateSelected(date: LocalDate) = onDateSelected?.invoke(date)
}
