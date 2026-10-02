package com.example

import com.example.utils.BanglaDateHelper
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun banglaDigitsConversion_isCorrect() {
        assertEquals("০১২৩৪৫৬৭৮৯", BanglaDateHelper.toBanglaDigits("0123456789"))
        assertEquals("২০২৬", BanglaDateHelper.toBanglaDigits(2026))
        assertEquals("২৫", BanglaDateHelper.toBanglaDigits(25))
    }

    @Test
    fun multilingualDate_isGenerated() {
        val dateInfo = BanglaDateHelper.getCurrentMultilingualDate()
        assertNotNull(dateInfo)
        assertTrue(dateInfo.banglaDateFormatted.contains("বঙ্গাব্দ"))
        assertTrue(dateInfo.hijriDateFormatted.contains("হিজরি"))
        assertNotNull(dateInfo.currentSeason)
    }
}
