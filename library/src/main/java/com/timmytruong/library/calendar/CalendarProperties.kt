package com.timmytruong.library.calendar

import com.timmytruong.library.core.CalendarTextData

data class CalendarProperties(
    val titleData: CalendarTextData?,
    val onMonthDayData: CalendarTextData?,
    val offMonthDayData: CalendarTextData?
)