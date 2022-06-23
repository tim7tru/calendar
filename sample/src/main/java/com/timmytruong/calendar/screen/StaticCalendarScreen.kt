package com.timmytruong.calendar.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timmytruong.calendar.R
import com.timmytruong.library.calendar.Calendar
import com.timmytruong.library.core.CalendarTextData
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@ExperimentalFoundationApi
@Composable
internal fun StaticCalendarScreen() {
    var startingDay by remember { mutableStateOf(DayOfWeek.SUNDAY) }
    var hasOffMonthDays by remember { mutableStateOf(true) }

    val dayTextData = CalendarTextData(
        modifier = Modifier.padding(all = 12.dp),
        textColor = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )

    LazyColumn(content = {
        item {
            Calendar(
                yearMonth = YearMonth.now(),
                titleData = CalendarTextData(fontWeight = FontWeight.Bold),
                hasOffMonthDays = hasOffMonthDays,
                onMonthDayData = dayTextData,
                offMonthDayData = dayTextData.copy(textColor = MaterialTheme.colors.primary),
                startingDay = startingDay
            )
        }

        item {
            Text(
                text = stringResource(R.string.starting_day_of_week),
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DayOfWeek.values().forEach { dayOfWeek ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RadioButton(
                            selected = dayOfWeek == startingDay,
                            onClick = { startingDay = dayOfWeek }
                        )
                        Text(
                            text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        )
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.show_off_month_days),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Switch(
                    checked = hasOffMonthDays,
                    onCheckedChange = { hasOffMonthDays = !hasOffMonthDays }
                )
            }
        }
    })
}