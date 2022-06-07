package com.timmytruong.library.extension

import java.time.LocalDate

internal fun LocalDate.isSelected(range: Pair<LocalDate, LocalDate>): Boolean = when {
    range.isValidRange() -> isAfterOrEqual(range.first) && isBeforeOrEqual(range.second)
    range.second.isEqual(LocalDate.MIN) -> isEqual(range.first)
    else -> false
}

internal fun LocalDate.isBeforeOrEqual(date: LocalDate) = isBefore(date) || isEqual(date)

internal fun LocalDate.isAfterOrEqual(date: LocalDate) = isEqual(date) || isAfter(date)

internal fun LocalDate.isBetween(range: Pair<LocalDate, LocalDate>) =
    isAfterOrEqual(range.first) && isBeforeOrEqual(range.second)

internal fun Pair<LocalDate, LocalDate>.isValidRange() = first.isBefore(second)