package com.timmytruong.calendar.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.timmytruong.library.calendar.HorizontalCalendarRange
import com.timmytruong.library.core.CalendarTextData
import java.time.DayOfWeek
import java.time.YearMonth

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun HorizontalRangeCalendarScreen() {
    val dayTextData = CalendarTextData(
        modifier = Modifier.padding(8.dp),
        textColor = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )

    HorizontalCalendarRange(
        range = YearMonth.of(2022, 1) to YearMonth.of(2022, 6),
        startingDay = DayOfWeek.SUNDAY,
        onMonthDayData = dayTextData,
        offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary)
    )
}