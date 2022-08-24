package com.timmytruong.calendar.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.timmytruong.calendar.ui.screen.reusable.SelectedDatesText
import com.timmytruong.calendar.ui.screen.util.showToast
import com.timmytruong.library.calendar.HorizontalCalendarRange
import com.timmytruong.library.calendar.selection.rememberMultiSelectionState
import com.timmytruong.library.calendar.selection.rememberRangeSelectionState
import com.timmytruong.library.calendar.selection.rememberSingleSelectionState
import com.timmytruong.library.core.CalendarTextData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun HorizontalRangeCalendarScreen() {
    val context = LocalContext.current
    val dayTextData = CalendarTextData(
        modifier = Modifier.padding(8.dp),
        textColor = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )

    val now = YearMonth.now()

    var selectedDate by remember {
        mutableStateOf(LocalDate.now())
    }

    val selectedDates = remember {
        mutableStateOf(listOf(LocalDate.now()))
    }

    val selectedRange = remember {
        mutableStateOf(
            LocalDate.of(now.year, now.month, 1) to LocalDate.of(now.year, now.month, 7)
        )
    }

    val singleCalendarState = rememberSingleSelectionState(
        initial = LocalDate.now(),
        onDateSelected = { it.showToast(context) },
        onStateUpdated = { selectedDate = it }
    )

    val multiCalendarState = rememberMultiSelectionState(
        initial = selectedDates.value,
        onDateSelected = { it.showToast(context) },
        onStateUpdated = { selectedDates.value = it }
    )

    val rangeCalendarState = rememberRangeSelectionState(
        initial = selectedRange.value,
        onDateSelected = { it.showToast(context) },
        onStateUpdated = { selectedRange.value = it }
    )

    Column {
        HorizontalCalendarRange(
            range = YearMonth.of(now.year, 1) to YearMonth.of(now.year, 12),
            startingDay = DayOfWeek.SUNDAY,
            startingMonth = now,
            onMonthDayData = dayTextData,
            offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary),
            calendarState = multiCalendarState,
            titleData = CalendarTextData(fontWeight = FontWeight.Bold)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SelectedDatesText()
            Text(
                text = selectedDates.value.map { it.format(DateTimeFormatter.ISO_LOCAL_DATE) }.toString(),
                fontSize = 20.sp,
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}