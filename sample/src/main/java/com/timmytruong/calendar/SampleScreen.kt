package com.timmytruong.calendar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.timmytruong.calendar.ui.theme.White500
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.calendar.selection.DateSelection
import com.timmytruong.library.core.CalendarTextData
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
enum class SampleScreen(val body: @Composable (NavController) -> Unit) {
    HOME({ Home(it) }),
    STATIC_CALENDAR({ StaticCalendar() }),
    SINGLE_SELECTION({ SingleSelectionCalendar() }),
    MULTI_SELECTION({ MultiSelectionCalendar() }),
    RANGE_SELECTION({ RangeSelectionCalendar() }),
    OFF_DAY_STATIC_CALENDAR({ OffDayStaticCalendar() });
}

@ExperimentalFoundationApi
@Composable
private fun Home(navController: NavController) {
    val screens = remember { SampleScreen.values().toList().filterNot { it == SampleScreen.HOME } }
    LazyColumn(
        content = {
            items(screens) { item ->
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate(item.name) },
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@ExperimentalFoundationApi
@Composable
private fun StaticCalendar() { Calendar(yearMonth = YearMonth.now()) }

@ExperimentalFoundationApi
@Composable
private fun SingleSelectionCalendar() {
    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Calendar(
        yearMonth = YearMonth.now(),
        dateSelection = DateSelection.SingleDay(
            initial = selectedDate,
            onDaySelected = { it.showToast(context) },
            onStateUpdated = { selectedDate = it }
        )
    )
}

@ExperimentalFoundationApi
@Composable
private fun MultiSelectionCalendar() {
    val context = LocalContext.current
    val selectedDates = remember { mutableStateListOf(LocalDate.now()) }
    Calendar(
        yearMonth = YearMonth.now(),
        dateSelection = DateSelection.MultipleDay(
            initial = selectedDates,
            onDaySelected = { it.showToast(context) },
            onStateUpdated = {
                selectedDates.clear()
                selectedDates.addAll(it)
            }
        )
    )
}

@ExperimentalFoundationApi
@Composable
private fun RangeSelectionCalendar() {
    val context = LocalContext.current
    val now = LocalDate.now()
    val selectedDates = remember { LocalDate.of(now.year, now.month, 1) to LocalDate.of(now.year, now.month, 7) }
    Calendar(
        yearMonth = YearMonth.now(),
        dateSelection = DateSelection.Range(
            initial = selectedDates,
            onDaySelected = { it.showToast(context) },
        )
    )
}

@ExperimentalFoundationApi
@Composable
private fun OffDayStaticCalendar() {
    Calendar(
        yearMonth = YearMonth.now(),
        onMonthDayData = CalendarTextData(
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            textColor = Color.White,
            modifier = Modifier.padding(all = 8.dp)
        ),
        offMonthDayData = CalendarTextData(
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            textColor = White500,
            modifier = Modifier.padding(all = 8.dp)
        )
    )
}

private fun LocalDate.showToast(context: Context) {
    Toast.makeText(context, format(DateTimeFormatter.ISO_LOCAL_DATE), Toast.LENGTH_SHORT).show()
}
