package com.timmytruong.calendar.ui.screen.util

import android.content.Context
import android.widget.Toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun LocalDate.showToast(context: Context) {
    Toast.makeText(context, format(DateTimeFormatter.ISO_LOCAL_DATE), Toast.LENGTH_SHORT).show()
}