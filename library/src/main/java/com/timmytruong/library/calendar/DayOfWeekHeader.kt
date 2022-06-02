package com.timmytruong.library.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.timmytruong.library.extension.reverseWithIndices
import com.timmytruong.library.month.DAYS_IN_WEEK
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@ExperimentalFoundationApi
@Composable
fun DayOfWeekHeader(startingDay: DayOfWeek) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(DAYS_IN_WEEK),
        content = {
            items(getDaysOfWeek(startingDay)) { item ->
                Text(
                    text = item.getDisplayName(TextStyle.NARROW, Locale.getDefault()),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

private fun getDaysOfWeek(startingDay: DayOfWeek) = DayOfWeek.values().apply {
    val pivot = startingDay.ordinal
    reverse()
    reverseWithIndices(0, size - pivot - 1)
    reverseWithIndices(size - pivot, size - 1)
}