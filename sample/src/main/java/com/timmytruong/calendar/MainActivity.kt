package com.timmytruong.calendar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.timmytruong.calendar.ui.theme.CalendarTheme
import com.timmytruong.library.calendar.DateSelection
import com.timmytruong.library.calendar.SimpleMonthCalendar
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Text(modifier = Modifier.padding(8.dp), text = "Simple Single-Day Select Calendar")
                        SimpleMonthCalendar(
                            yearMonth = YearMonth.now(),
                            startingDay = DayOfWeek.SUNDAY,
                            dateSelection = DateSelection.SingleDay(
                                onDaySelected = { onDaySelected(it) },
                                initial = viewModel.initialDate
                            )
                        )
                        Text(modifier = Modifier.padding(8.dp), text = "Simple Multi-Day Select Calendar")
                        SimpleMonthCalendar(
                            yearMonth = YearMonth.now(),
                            startingDay = DayOfWeek.SUNDAY,
                            dateSelection = DateSelection.MultipleDay(
                                onDaySelected = ::onDaySelected,
                                initial = listOf(viewModel.initialDate)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun onDaySelected(date: LocalDate) {
        Toast.makeText(this, date.format(DateTimeFormatter.BASIC_ISO_DATE), Toast.LENGTH_SHORT).show()
    }

    private fun onDaySelected(date: LocalDate, selectedDates: List<LocalDate>) {
        Toast.makeText(this, date.format(DateTimeFormatter.BASIC_ISO_DATE), Toast.LENGTH_SHORT).show()
        selectedDates.forEach { println(it) }
    }
}
