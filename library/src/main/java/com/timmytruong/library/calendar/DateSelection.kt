package com.timmytruong.library.calendar

import java.time.LocalDate

internal typealias DaySelection = (LocalDate) -> Unit
internal typealias DateStateUpdate<T> = (T) -> Unit

// User-facing API such that the user can observe:
// (1) What the selected dates are
// (2) When the day is selected
sealed class DateSelection<T> {

    abstract val onDaySelected: DaySelection
    abstract val onStateUpdated: DateStateUpdate<T>
    abstract val initial: T?

    data class SingleDay(
        override val initial: LocalDate? = null,
        override val onDaySelected: DaySelection = { },
        override val onStateUpdated: DateStateUpdate<LocalDate> = { }
    ) : DateSelection<LocalDate>()

    data class MultipleDay(
        override val initial: List<LocalDate>? = null,
        override val onDaySelected: DaySelection = { },
        override val onStateUpdated: DateStateUpdate<List<LocalDate>> = { }
    ) : DateSelection<List<LocalDate>>()
}