package com.timmytruong.library.month
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal const val DAYS_IN_WEEK = 7

data class MonthData(val yearMonth: YearMonth)

@ExperimentalFoundationApi
@Composable
fun SimpleMonth(data: MonthData, startingDay: DayOfWeek) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(data.toDayData(startingDay)) { item ->
                item?.let { SimpleDayTile(it) } ?: EmptyDayTile()
            }
        }
    )
}

private fun MonthData.toDayData(startingDay: DayOfWeek): List<DayData?> = mutableListOf<DayData?>().apply {
    val startOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, 1)
    val endOfMonth = LocalDate.of(yearMonth.year, yearMonth.month, startOfMonth.lengthOfMonth())

    if (startOfMonth.dayOfWeek != startingDay) repeat(startOfMonth.dayOfWeek.value) { add(null) }

    var curr = startOfMonth

    while (curr <= endOfMonth) {
        add(DayData(date = curr))
        curr = curr.plusDays(1)
    }

    while (size % DAYS_IN_WEEK != 0) {
        add(null)
    }
}

internal val MonthData.month get() = yearMonth.month
