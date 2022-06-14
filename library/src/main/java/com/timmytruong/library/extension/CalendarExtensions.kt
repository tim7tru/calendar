package com.timmytruong.library.extension

import com.timmytruong.library.calendar.CalendarProperties

internal fun CalendarProperties.generateDays() = yearMonth.toDays(startingDay, offMonthDayData != null)
