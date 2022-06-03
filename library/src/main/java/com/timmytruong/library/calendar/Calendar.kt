package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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

sealed class DateSelection {
    data class SingleDay(
        val selectedDate: State<LocalDate?>,
        val onDaySelected: (LocalDate) -> Unit
    ): DateSelection()
}

@ExperimentalFoundationApi
@Composable
fun SimpleMonthCalendar(
    yearMonth: YearMonth,
    startingDay: DayOfWeek = DayOfWeek.SUNDAY,
    dateSelection: DateSelection? = null,
    titleData: TitleData? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            data = titleData,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )
        DayOfWeekHeader(
            startingDay = startingDay
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(DAYS_IN_WEEK),
            content = {
                items(yearMonth.toDayData(startingDay, dateSelection)) { item ->
                    item?.let { SimpleDayTile(it) } ?: EmptyDayTile()
                }
            }
        )
    }
}

private fun YearMonth.toDayData(
    startingDay: DayOfWeek,
    dateSelection: DateSelection? = null
): List<DayData?> = mutableListOf<DayData?>().apply {
    val startOfMonth = LocalDate.of(year, month, 1)
    val endOfMonth = LocalDate.of(year, month, startOfMonth.lengthOfMonth())

    if (startOfMonth.dayOfWeek != startingDay) repeat(startOfMonth.dayOfWeek.value) { add(null) }

    var curr = startOfMonth

    while (curr <= endOfMonth) {
        add(
            DayData(
                date = curr,
                selection = dateSelection as? DateSelection.SingleDay
            )
        )
        curr = curr.plusDays(1)
    }

    while (size % DAYS_IN_WEEK != 0) { add(null) }
}
