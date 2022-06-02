package com.timmytruong.library.month
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.EmptyDayTile
import com.timmytruong.library.day.SimpleDayTile
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

internal const val DAYS_IN_WEEK = 7

@ExperimentalFoundationApi
@Composable
fun SimpleMonth(
    yearMonth: YearMonth,
    startingDay: DayOfWeek,
    onDaySelected: ((LocalDate) -> Unit)? = null,
    selectedDate: State<LocalDate?>? = null
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(yearMonth.toDayData(startingDay, selectedDate)) { item ->
                item?.let { SimpleDayTile(it, onDaySelected) } ?: EmptyDayTile()
            }
        }
    )
}

private fun YearMonth.toDayData(
    startingDay: DayOfWeek,
    selectedDate: State<LocalDate?>? = null
): List<DayData?> = mutableListOf<DayData?>().apply {
    val startOfMonth = LocalDate.of(year, month, 1)
    val endOfMonth = LocalDate.of(year, month, startOfMonth.lengthOfMonth())

    if (startOfMonth.dayOfWeek != startingDay) repeat(startOfMonth.dayOfWeek.value) { add(null) }

    var curr = startOfMonth

    while (curr <= endOfMonth) {
        add(DayData(date = curr, isSelected = curr.equals(selectedDate?.value)))
        curr = curr.plusDays(1)
    }

    while (size % DAYS_IN_WEEK != 0) { add(null) }
}
