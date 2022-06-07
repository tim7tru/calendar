package com.timmytruong.library.extension

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class YearMonthExtensionTest {

    @Test
    fun `Given June 2022 and starting date of Sunday, when toDays is called, then days are valid`() {
        with(YearMonth.of(2022, 6).toDays(DayOfWeek.SUNDAY)) {
            var index = 0
            repeat(3) { get(index++) shouldBe null }
            repeat(30) { get(index++).shouldBeInstanceOf<LocalDate>() }
            repeat(2) { get(index++) shouldBe null }
        }
    }
}