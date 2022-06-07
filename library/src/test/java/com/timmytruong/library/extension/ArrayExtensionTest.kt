package com.timmytruong.library.extension

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ArrayExtensionTest {

    @Test
    fun `Given empty array, when reverseWithIndices is called, then array is not modified`() {
        val subject = arrayOf<Int>()
        subject.reverseWithIndices(0, 1) shouldBe subject
    }

    @Test
    fun `Given int array, when reverseWithIndices is called with invalid indices, then array is not modified`() {
        val subject = arrayOf(1, 2, 3, 4)
        subject.reverseWithIndices(-1, 1) shouldBe subject
        subject.reverseWithIndices(0, 5) shouldBe subject
        subject.reverseWithIndices(-1, 5) shouldBe subject
    }

    @Test
    fun `Given int array, when reverseWithIndices is called with valid indices, then array is correctly modified`() {
        val subject = arrayOf(1, 2, 3, 4)
        subject.reverseWithIndices(0, 1) shouldBe arrayOf(2, 1, 3, 4)
    }
}