package com.timmytruong.calendar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class MainActivityViewModel: ViewModel() {

    private val _selectedDate: MutableState<LocalDate?> = mutableStateOf(null)
    val selectedDate: State<LocalDate?> = _selectedDate

    fun onDaySelected(date: LocalDate) { _selectedDate.value = date }
}