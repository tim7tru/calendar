package com.timmytruong.library.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.timmytruong.library.core.Title
import com.timmytruong.library.month.SimpleMonth
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@ExperimentalFoundationApi
@Composable
fun SimpleCalendar(
    @SuppressLint("ModifierParameter") titleModifier: Modifier,
    yearMonth: YearMonth,
    startingDay: DayOfWeek,
    selectedDate: State<LocalDate?>,
    onDaySelected: ((LocalDate) -> Unit)? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Title(
            modifier = titleModifier,
            text = yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
        )
        DayOfWeekHeader(startingDay = startingDay)
        SimpleMonth(
            yearMonth = yearMonth,
            startingDay = startingDay,
            selectedDate = selectedDate,
            onDaySelected = onDaySelected
        )
    }
}