package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import com.timmytruong.library.core.Title
import com.timmytruong.library.core.TitleData
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

internal const val DAYS_IN_WEEK = 7

@ExperimentalFoundationApi
@Composable
fun SimpleMonthCalendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    dateSelection: DateSelection<*>? = null,
    titleData: TitleData? = null
) {
    val selectedDates = dateSelection?.let {
        remember {
            mutableStateListOf<LocalDate>().apply {
                when (it) {
                    is DateSelection.SingleDay -> it.initial?.let { add(it) }
                    is DateSelection.MultipleDay -> it.initial?.let { addAll(it) }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            data = titleData,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )
        DayOfWeekHeader(startingDay = startingDay)
        LazyVerticalGrid(
            cells = GridCells.Fixed(DAYS_IN_WEEK),
            content = {
                items(yearMonth.toDayData(startingDay)) { item ->
                    item?.let { date ->
                        SimpleDayTile(
                            data = DayData(
                                date = date,
                                dayClicks = { dateSelection?.onDaySelected(date, selectedDates) },
                                isSelected = selectedDates?.contains(date) ?: false
                            )
                        )
                    } ?: EmptyDayTile()
                }
            }
        )
    }
}

private fun DateSelection<*>.onDaySelected(date: LocalDate, selectedDates: SnapshotStateList<LocalDate>?) {
    onDaySelected(date)
    when (this) {
        is DateSelection.SingleDay -> selectedDates?.run {
            if (!contains(date)) add(date)
            removeFirst()
        }
        is DateSelection.MultipleDay -> selectedDates?.run {
            if (contains(date)) remove(date)
            else add(date)
        }
    }
}

private fun YearMonth.toDayData(startingDay: DayOfWeek): List<LocalDate?> = mutableListOf<LocalDate?>().apply {
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
