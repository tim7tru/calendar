package com.timmytruong.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.timmytruong.calendar.ui.theme.CalendarTheme
import com.timmytruong.library.calendar.SimpleCalendar
import com.timmytruong.library.core.Title
import com.timmytruong.library.month.MonthData
import java.time.DayOfWeek
import java.time.YearMonth

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val now = YearMonth.now().minusMonths(1)
        setContent {
            CalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Title("Simple Calendar")
                        SimpleCalendar(monthData = MonthData(now), startingDay = DayOfWeek.SUNDAY)
                    }
                }
            }
        }
    }
}
