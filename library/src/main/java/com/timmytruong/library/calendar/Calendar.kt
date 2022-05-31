package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.timmytruong.library.core.Title
import com.timmytruong.library.month.MonthData
import com.timmytruong.library.month.SimpleMonth
import com.timmytruong.library.month.month
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@ExperimentalFoundationApi
@Composable
fun SimpleCalendar(monthData: MonthData, startingDay: DayOfWeek) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(text = monthData.month.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        DayOfWeekHeader(startingDay = startingDay)
        SimpleMonth(data = monthData, startingDay = startingDay)
    }
}