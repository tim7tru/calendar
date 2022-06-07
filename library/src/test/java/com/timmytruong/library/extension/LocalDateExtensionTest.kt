package com.timmytruong.library.extension

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDate

class LocalDateExtensionTest {

    @Test
    fun `Given date range with one selected, when isSelected is called, then only selected date returns true`() {
        val date1 = LocalDate.of(2000, 1, 1)
        val date2 = LocalDate.of(2001, 1, 1)
        val date3 = LocalDate.of(2002, 1, 1)

        val range = date2 to LocalDate.MIN

        date1.isSelected(range) shouldBe false
        date2.isSelected(range) shouldBe true
        date3.isSelected(range) shouldBe false
    }

    @Test
    fun `Given doubly selected date range, when isSelected is called, then only dates in range returns true`() {
        val date1 = LocalDate.of(1900, 1, 1)
        val date2 = LocalDate.of(2000, 1, 1)
        val date3 = LocalDate.of(2100, 1, 1)

        val range = LocalDate.of(1950, 1, 1) to LocalDate.of(2050, 1, 1)

        date1.isSelected(range) shouldBe false
        date2.isSelected(range) shouldBe true
        date3.isSelected(range) shouldBe false
    }

    @Test
    fun `Given a date prior to another, when isBeforeOrEqual is called, then the result is true`() {
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        first.isBeforeOrEqual(second) shouldBe true
    }

    @Test
    fun `Given same dates, when isBeforeOrEqual is called, then the result is true`() {
        val date = LocalDate.of(2000, 1, 7)

        date.isBeforeOrEqual(date) shouldBe true
    }

    @Test
    fun `Given a date after another, when isBeforeOrEqual is called, then the result is false`() {
        val first = LocalDate.of(2022, 6, 6)
        val second = LocalDate.of(2000, 1, 7)

        first.isBeforeOrEqual(second) shouldBe false
    }

    @Test
    fun `Given a date prior to another, when isAfterOrEqual is called, then the result is false`() {
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        first.isAfterOrEqual(second) shouldBe false
    }

    @Test
    fun `Given same dates, when isAfterOrEqual is called, then the result is true`() {
        val date = LocalDate.of(2000, 1, 7)

        date.isAfterOrEqual(date) shouldBe true
    }

    @Test
    fun `Given a date after another, when isAfterOrEqual is called, then the result is true`() {
        val first = LocalDate.of(2022, 6, 6)
        val second = LocalDate.of(2000, 1, 7)

        first.isAfterOrEqual(second) shouldBe true
    }

    @Test
    fun `Given date in range, when isBetween is called, then the result is true`() {
        val date = LocalDate.of(2010, 10, 10)
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        date.isBetween(first to second) shouldBe true
    }

    @Test
    fun `Given date not in range, when isBetween is called, then the result is true`() {
        val date = LocalDate.of(1900, 10, 10)
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        date.isBetween(first to second) shouldBe false
    }

    @Test
    fun `Given date equal to range extreme, when isBetween is called, then the result is true`() {
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        first.isBetween(first to second) shouldBe true
        second.isBetween(first to second) shouldBe  true
    }

    @Test
    fun `Given date equal to both range extremes, when isBetween is called, then the result is true`() {
        val date = LocalDate.of(2000, 1, 7)

        date.isBetween(date to date) shouldBe true
    }

    @Test
    fun `Given valid date range, when isValidRange is called, then the result is true`() {
        val first = LocalDate.of(2000, 1, 7)
        val second = LocalDate.of(2022, 6, 6)

        (first to second).isValidRange() shouldBe true
    }

    @Test
    fun `Given date range of same dates, when isValidRange is called, then the result is true`() {
        val date = LocalDate.of(2000, 1, 7)

        (date to date).isValidRange() shouldBe true
    }

    @Test
    fun `Given invalid date range, when isValid Range is called, then the result is false`() {
        val first = LocalDate.of(2022, 6, 6)
        val second = LocalDate.of(2000, 1, 7)

        (first to second).isValidRange() shouldBe false
    }
}