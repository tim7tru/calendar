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

    @Test
    fun `Given int array, when to2DList is called with small column number, then the proper array is returned`() {
        val subject = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        val result = subject.to2DList(2)

        result.size shouldBe 4
        result.forEach { it.size shouldBe 2 }
    }

    @Test
    fun `Given int array, when to2DList is called with large column number, then the proper array is returned`() {
        val subject = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        val result = subject.to2DList(10)

        result.size shouldBe 1
        result.forEach { it.size shouldBe 8 }
    }
}