package com.timmytruong.calendar

import androidx.lifecycle.ViewModel
import java.time.LocalDate

class MainActivityViewModel: ViewModel() {

    val initialDate: LocalDate = LocalDate.now()
}