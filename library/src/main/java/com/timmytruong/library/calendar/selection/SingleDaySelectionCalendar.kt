package com.timmytruong.library.calendar.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import com.timmytruong.library.calendar.DayTextData
import com.timmytruong.library.day.Day
import com.timmytruong.library.day.DayData
import com.timmytruong.library.day.DayGrid
import com.timmytruong.library.extension.isIn
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
internal fun SingleDaySelectionCalendar(
    days: List<LocalDate?>,
    currentMonth: YearMonth,
    dateSelection: DateSelection.SingleDay,
    dayTextData: DayTextData
) {
    var selectedDate by remember { mutableStateOf(dateSelection.initial) }

    DayGrid(gridItems = days) {
        val data = it?.let { date ->
            DayData.SelectableDayData(
                date = date,
                dayClicks = {
                    selectedDate = date
                    dateSelection.onDaySelected(date)
                    selectedDate?.let {
                        dateSelection.onStateUpdated(it)
                    }
                },
                isSelected = date.isEqual(selectedDate),
                textData = dayTextData.resolve(date isIn currentMonth)
            )
        } ?: DayData.EmptyDay()
        Day(data)
    }
}
