package com.timmytruong.library.day

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.timmytruong.library.calendar.DAYS_IN_WEEK

@Composable
internal fun <T> DayGrid(
    gridItems: List<T>,
    composable: @Composable (T) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().heightIn(max = 1000.dp),
        columns = GridCells.Fixed(DAYS_IN_WEEK),
        content = { items(gridItems) { item -> composable(item) } },
        userScrollEnabled = false
    )
}