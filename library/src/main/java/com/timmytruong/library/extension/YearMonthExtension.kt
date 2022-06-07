package com.timmytruong.library.extension

import com.timmytruong.library.calendar.DAYS_IN_WEEK
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal fun YearMonth.toDays(startingDay: DayOfWeek): List<LocalDate?> = mutableListOf<LocalDate?>().apply {
    val startOfMonth = LocalDate.of(year, month, 1)
    val endOfMonth = LocalDate.of(year, month, startOfMonth.lengthOfMonth())

    if (startOfMonth.dayOfWeek != startingDay) repeat(startOfMonth.dayOfWeek.value) { add(null) }

    var curr = startOfMonth

    while (curr <= endOfMonth) {
        add(curr)
        curr = curr.plusDays(1)
    }

    while (size % DAYS_IN_WEEK != 0) {
        add(null)
    }
}
