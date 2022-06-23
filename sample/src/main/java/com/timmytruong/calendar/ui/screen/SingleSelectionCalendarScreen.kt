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
import com.timmytruong.calendar.ui.screen.reusable.SelectedDatesText
import com.timmytruong.calendar.ui.screen.util.showToast
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.calendar.selection.DateSelection
import com.timmytruong.library.core.CalendarTextData
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


@ExperimentalFoundationApi
@Composable
internal fun SingleSelectionCalendar() {
    val context = LocalContext.current
    var selectedDateText by remember {
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
    }
    val dayTextData = CalendarTextData(
        modifier = Modifier.padding(8.dp),
        textColor = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )

    Column {
        Calendar(
            yearMonth = YearMonth.now(),
            dateSelection = DateSelection.SingleDay(
                initial = LocalDate.now(),
                onDaySelected = { it.showToast(context) },
                onStateUpdated = {
                    selectedDateText = it.format(DateTimeFormatter.ISO_LOCAL_DATE)
                }
            ),
            titleData = CalendarTextData(fontWeight = FontWeight.Bold),
            onMonthDayData = dayTextData,
            offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SelectedDatesText()
            Text(
                text = selectedDateText,
                fontSize = 20.sp,
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}
