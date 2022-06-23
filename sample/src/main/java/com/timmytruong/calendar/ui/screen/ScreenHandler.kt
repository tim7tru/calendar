package com.timmytruong.calendar.ui.screen

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.timmytruong.calendar.R

@ExperimentalPagerApi
@ExperimentalFoundationApi
enum class ScreenHandler(
    val body: @Composable (NavController) -> Unit,
    @StringRes val title: Int
) {
    HOME({ Home(it) }, R.string.home),
    STATIC_CALENDAR({ StaticCalendarScreen() }, R.string.static_calendar),
    SINGLE_SELECTION({ SingleSelectionCalendar() }, R.string.single_selection_calendar),
    MULTI_SELECTION({ MultiSelectionCalendar() }, R.string.multiple_selection_calendar),
    RANGE_SELECTION({ RangeSelectionCalendar() }, R.string.range_selection_calendar),
    HORIZONTAL_RANGE({ HorizontalRangeCalendarScreen() }, R.string.horizontal_calendar_range);

    fun resolveTitle(context: Context) = context.getString(title)
}
