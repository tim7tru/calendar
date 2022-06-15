package com.timmytruong.calendar

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
enum class SampleScreen(
    val body: @Composable (NavController) -> Unit,
    @StringRes val title: Int
) {
    HOME({ Home(it) }, R.string.home),
    STATIC_CALENDAR({ StaticCalendar() }, R.string.static_calendar),
    SINGLE_SELECTION({ SingleSelectionCalendar() }, R.string.single_selection_calendar),
    MULTI_SELECTION({ MultiSelectionCalendar() }, R.string.multiple_selection_calendar),
    RANGE_SELECTION({ RangeSelectionCalendar() }, R.string.range_selection_calendar),
    OFF_DAY_STATIC_CALENDAR({ OffDayStaticCalendar() }, R.string.home);

    fun resolveTitle(context: Context) = context.getString(title)
}

@ExperimentalFoundationApi
@Composable
private fun Home(navController: NavController) {
    val context = LocalContext.current
    val screens = remember { SampleScreen.values().toList().filterNot { it == SampleScreen.HOME } }
    LazyColumn(
        Modifier.fillMaxWidth(),
        content = {
            items(screens) { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(item.name) },
                ) {
                    Icon(
                        Icons.Filled.DateRange,
                        contentDescription = item.name,
                        tint = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Text(
                        text = item.resolveTitle(context),
                        modifier = Modifier.padding(all = 8.dp),
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.primary
                    )
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
                onStateUpdated = { selectedDateText = it.format(DateTimeFormatter.ISO_LOCAL_DATE) }
            ),
            titleData = CalendarTextData(fontWeight = FontWeight.Bold),
            onMonthDayData = dayTextData,
            offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.selected_dates),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = selectedDateText,
                fontSize = 20.sp,
                color = MaterialTheme.colors.secondary,
            )
        }
    }
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
