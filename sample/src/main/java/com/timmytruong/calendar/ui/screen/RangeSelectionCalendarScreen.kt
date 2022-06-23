package com.timmytruong.calendar.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timmytruong.calendar.ui.screen.reusable.SelectedDatesText
import com.timmytruong.calendar.ui.screen.util.showToast
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.calendar.selection.DateSelection
import com.timmytruong.library.core.CalendarTextData
import com.timmytruong.library.extension.getDatesFromRange
import java.time.LocalDate
import java.time.YearMonth

@ExperimentalFoundationApi
@Composable
internal fun RangeSelectionCalendar() {
    val context = LocalContext.current
    val now = LocalDate.now()
    var selectedDates by remember {
        mutableStateOf(
            LocalDate.of(now.year, now.month, 1) to LocalDate.of(now.year, now.month, 7)
        )
    }
    val selectedDatesText = selectedDates.getDatesFromRange()

    val dayTextData = CalendarTextData(
        modifier = Modifier.padding(8.dp),
        textColor = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )

    Column {
        Calendar(
            yearMonth = YearMonth.now(),
            dateSelection = DateSelection.Range(
                initial = selectedDates,
                onDaySelected = { it.showToast(context) },
                onStateUpdated = {
                    selectedDates = it
                }
            ),
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
