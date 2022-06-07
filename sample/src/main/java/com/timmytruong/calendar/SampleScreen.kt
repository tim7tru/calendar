package com.timmytruong.calendar

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.calendar.selection.DateSelection
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
enum class SampleScreen(
    val body: @Composable (NavController) -> Unit
) {
    HOME({ Home(it) }),
    STATIC_CALENDAR({ StaticCalendar() }),
    SINGLE_SELECTION({ SingleSelectionCalendar() }),
    MULTI_SELECTION({ MultiSelectionCalendar() }),
    RANGE_SELECTION({ RangeSelectionCalendar() });
}

@ExperimentalFoundationApi
@Composable
fun Home(navController: NavController) {
    LazyColumn(
        content = {
            items(SampleScreen.values().toList().filterNot { it == SampleScreen.HOME }) { item ->
                Button(onClick = { navController.navigate(item.name) }) {
                    Text(text = item.name)
                }
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


private fun LocalDate.showToast(context: Context) {
    Toast.makeText(context, format(DateTimeFormatter.ISO_LOCAL_DATE), Toast.LENGTH_SHORT).show()
}