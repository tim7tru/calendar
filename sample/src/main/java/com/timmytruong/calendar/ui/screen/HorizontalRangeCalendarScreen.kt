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
import com.timmytruong.library.calendar.selection.DateSelection
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

    Column {
        HorizontalCalendarRange(
            range = YearMonth.of(now.year, 1) to YearMonth.of(now.year, 12),
            startingDay = DayOfWeek.SUNDAY,
            startingMonth = now,
            onMonthDayData = dayTextData,
            offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary),
            dateSelection = DateSelection.SingleDay(
                initial = selectedDate,
                onDaySelected = { it.showToast(context) },
                onStateUpdated = { selectedDate = it }
            ),
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
                text = selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                fontSize = 20.sp,
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}