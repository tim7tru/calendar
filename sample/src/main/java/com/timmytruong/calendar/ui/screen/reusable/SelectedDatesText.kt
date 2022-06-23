package com.timmytruong.calendar.ui.screen.reusable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.timmytruong.calendar.R

@Composable
internal fun SelectedDatesText() {
    Text(
        text = stringResource(id = R.string.selected_dates),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = MaterialTheme.colors.primary,
    )
}
