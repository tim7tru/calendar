package com.timmytruong.library.extension

import com.timmytruong.library.calendar.DAYS_IN_WEEK
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal fun YearMonth.toDays(
    startingDay: DayOfWeek,
    hasOffMonthDays: Boolean
): List<LocalDate?> = mutableListOf<LocalDate?>().apply {
    val startOfMonth = LocalDate.of(year, month, 1)
    val endOfMonth = LocalDate.of(year, month, startOfMonth.lengthOfMonth())

    val isStartDayFirstDayOfWeek = startOfMonth.dayOfWeek == startingDay
    var curr: LocalDate

    if (!isStartDayFirstDayOfWeek) {
        curr = startOfMonth.minusDays(startOfMonth.dayOfWeek.value.toLong())
        repeat(startOfMonth.dayOfWeek.value) {
            if (!hasOffMonthDays) add(null)
            else {
                add(curr)
                curr = curr.plusDays(1)
            }
        }
    }

    curr = startOfMonth

    while (curr <= endOfMonth) {
        add(curr)
        curr = curr.plusDays(1)
    }

    while (size % DAYS_IN_WEEK != 0) {
        if (!hasOffMonthDays) add(null)
        else {
            add(curr)
            curr = curr.plusDays(1)
        }
    }
}
