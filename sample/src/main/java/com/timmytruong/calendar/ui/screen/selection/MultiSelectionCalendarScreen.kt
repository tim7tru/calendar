package com.timmytruong.calendar.ui.screen.selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timmytruong.calendar.ui.screen.reusable.SelectedDatesText
import com.timmytruong.calendar.ui.screen.util.showToast
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.calendar.selection.rememberMultiSelectionState
import com.timmytruong.library.core.CalendarTextData
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
@Composable
internal fun MultiSelectionCalendar() {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colors.primaryVariant
    val selectedDates = remember { mutableStateListOf(LocalDate.now()) }
    val selectedDatesText = selectedDates.map { it.format(DateTimeFormatter.ISO_LOCAL_DATE) }

    val dayTextData = remember {
        CalendarTextData(
            modifier = Modifier.padding(8.dp),
            textColor = primaryColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }

    val calendarState = rememberMultiSelectionState(
        initial = selectedDates,
        onDateSelected = { it.showToast(context) },
        onStateUpdated = {
            selectedDates.clear()
            selectedDates.addAll(it)
        }
    )

    Column {
        Calendar(
            yearMonth = YearMonth.now(),
            calendarState = calendarState,
            onMonthDayData = dayTextData,
            offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary)
        )
        SelectedDatesText()
        Text(
            text = selectedDatesText.toString(),
            fontSize = 20.sp,
            color = MaterialTheme.colors.secondary
        )
    }
}
