package com.timmytruong.calendar

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MainActivityViewModelTest {

    private lateinit var subject: MainActivityViewModel

    @BeforeEach
    fun setup() {
        subject = MainActivityViewModel()
    }

    @Test
    fun `Given viewModel, when selectedDate is accessed, then default value is null`() {
        subject.selectedDate.value shouldBe null
    }

    @Test
    fun `Given viewModel, when onDaySelected is called, then selectedDate is set`() {
        val date = LocalDate.of(2000, 1, 7)
        subject.onDaySelected(date)
        subject.selectedDate.value shouldBe date
    }
}