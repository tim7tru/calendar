package com.timmytruong.library.extension

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class YearMonthExtensionTest {

    @Test
    fun `Given June 2022, starting date of Sunday, and no off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.SUNDAY, hasOffMonthDays = false)) {
            var index = 0
            repeat(3) { get(index++) shouldBe null }
            repeat(30) { get(index++).shouldBeInstanceOf<LocalDate>() }
            repeat(2) { get(index++) shouldBe null }
        }
    }

    @Test
    fun `Given June 2022, starting date of Sunday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.SUNDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 29)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }

    @Test
    fun `Given June 2022, starting date of Monday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.MONDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 30)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }

    @Test
    fun `Given June 2022, starting date of Tuesday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.TUESDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 31)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }

    @Test
    fun `Given June 2022, starting date of Wednesday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.WEDNESDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 6, 1)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }

    @Test
    fun `Given June 2022, starting date of Thursday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.THURSDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 26)
            repeat(42) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }


    @Test
    fun `Given June 2022, starting date of Friday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.FRIDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 27)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }

    @Test
    fun `Given June 2022, starting date of Saturday, and off month days, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.SATURDAY, hasOffMonthDays = true)) {
            var index = 0
            var curr = LocalDate.of(2022, 5, 28)
            repeat(35) {
                get(index++) shouldBe curr
                curr = curr.plusDays(1)
            }
        }
    }
}