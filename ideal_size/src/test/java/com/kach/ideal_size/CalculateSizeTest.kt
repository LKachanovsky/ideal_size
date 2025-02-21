package com.kach.ideal_size

import org.junit.Assert.*
import org.junit.Test

class CalculateSizeTest {
    @Test
    fun `process correct values, return Size_M`() {
        val height = "185"
        val weight = "65"

        assertEquals(Size.M, processSizeRecommendation(height, weight))
    }

    @Test
    fun `process empty values, return null`() {
        val height = "  "
        val weight = "  "

        assertNull(processSizeRecommendation(height, weight))
    }

    @Test
    fun `process incorrect values, return null`() {
        val height = "0.655,234"
        val weight = "asdf"

        assertNull(processSizeRecommendation(height, weight))
    }

    @Test
    fun `process negative values, return null`() {
        val height = "-185"
        val weight = "-65"

        assertNull(processSizeRecommendation(height, weight))
    }
}