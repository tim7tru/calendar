package com.timmytruong.library.extension

import java.time.LocalDate
import java.time.YearMonth

internal fun LocalDate.isSelected(range: Pair<LocalDate, LocalDate>): Boolean = when {
    range.second.isEqual(LocalDate.MIN) -> isEqual(range.first)
    else -> isBetween(range)
}

internal fun LocalDate.isBeforeOrEqual(date: LocalDate) = isBefore(date) || isEqual(date)

internal fun LocalDate.isAfterOrEqual(date: LocalDate) = isEqual(date) || isAfter(date)

internal fun LocalDate.isBetween(range: Pair<LocalDate, LocalDate>) =
    isAfterOrEqual(range.first) && isBeforeOrEqual(range.second)

internal fun Pair<LocalDate, LocalDate>.isValidRange() = first.isBeforeOrEqual(second)

internal infix fun LocalDate.isIn(date: YearMonth): Boolean = month == date.month && year == date.year

fun Pair<LocalDate, LocalDate>.getDatesFromRange(): List<LocalDate> = mutableListOf<LocalDate>().apply {
    if (!isValidRange()) return@apply
    var curr = first
    while (curr.isBeforeOrEqual(second)) {
        add(curr)
        curr = curr.plusDays(1)
    }
}
