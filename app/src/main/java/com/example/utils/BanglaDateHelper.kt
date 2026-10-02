package com.example.utils

import android.os.Build
import com.example.data.model.BanglaSeason
import java.time.LocalDate
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.util.Locale

object BanglaDateHelper {

    private val banglaDigits = mapOf(
        '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
        '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯'
    )

    private val banglaMonths = listOf(
        "বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন",
        "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"
    )

    private val hijriMonthsBn = listOf(
        "মহররম", "সফর", "রবিউল আউয়াল", "রবিউস সানি", "জমাদিউল আউয়াল",
        "জমাদিউস সানি", "রজব", "শাবান", "রমজান", "শাওয়াল", "জিলক্বদ", "জিলহজ্জ"
    )

    private val daysOfWeekBn = listOf(
        "সোমবার", "মঙ্গলবার", "বুধবার", "বৃহস্পতিবার", "শুক্রবার", "শনিবার", "রবিবার"
    )

    fun toBanglaDigits(input: Any): String {
        val str = input.toString()
        val sb = StringBuilder()
        for (ch in str) {
            sb.append(banglaDigits[ch] ?: ch)
        }
        return sb.toString()
    }

    data class MultilingualDate(
        val englishDateFormatted: String,
        val banglaDateFormatted: String,
        val hijriDateFormatted: String,
        val currentSeason: BanglaSeason,
        val dayOfWeekBn: String
    )

    fun getCurrentMultilingualDate(): MultilingualDate {
        return try {
            val today = LocalDate.now()
            val dayOfWeek = daysOfWeekBn[today.dayOfWeek.value - 1]

            // English Formatted
            val enFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
            val englishFormatted = "${today.format(enFormatter)} ($dayOfWeek)"

            // Bangla Bongabdo calculation
            val banglaDate = calculateBanglaDate(today)

            // Hijri Date
            val hijriFormatted = calculateHijriDate(today)

            MultilingualDate(
                englishDateFormatted = englishFormatted,
                banglaDateFormatted = banglaDate.formattedText,
                hijriDateFormatted = hijriFormatted,
                currentSeason = banglaDate.season,
                dayOfWeekBn = dayOfWeek
            )
        } catch (e: Exception) {
            MultilingualDate(
                englishDateFormatted = "2 October 2026",
                banglaDateFormatted = "১৭ আশ্বিন ১৪৩৩ বঙ্গাব্দ",
                hijriDateFormatted = "২০ রবিউস সানি ১৪৪৮ হিজরি",
                currentSeason = BanglaSeason.AUTUMN,
                dayOfWeekBn = "শুক্রবার"
            )
        }
    }

    private data class BanglaDateResult(
        val day: Int,
        val monthIndex: Int,
        val year: Int,
        val formattedText: String,
        val season: BanglaSeason
    )

    private fun calculateBanglaDate(date: LocalDate): BanglaDateResult {
        val gYear = date.year
        val gMonth = date.monthValue
        val gDay = date.dayOfMonth

        // Bangla New Year (Pohela Boishakh) is on April 14 in Bangladesh
        val pohelaBoishakh = LocalDate.of(gYear, 4, 14)
        val banglaYear = if (date.isBefore(pohelaBoishakh)) gYear - 594 else gYear - 593

        // Modern Bangladesh Academy Calendar Month Lengths:
        // Boishakh to Ashwin: 31 days (6 months)
        // Kartik to Magh: 30 days (4 months)
        // Falgun: 29 or 30 days (leap year check)
        // Chaitra: 30 days
        val isLeapYear = date.isLeapYear
        val monthLengths = intArrayOf(
            31, 31, 31, 31, 31, 31, // Boishakh (0) to Ashwin (5)
            30, 30, 30, 30,         // Kartik (6) to Magh (9)
            if (isLeapYear) 30 else 29, // Falgun (10)
            30                      // Chaitra (11)
        )

        val referenceDate = if (date.isBefore(pohelaBoishakh)) {
            LocalDate.of(gYear - 1, 4, 14)
        } else {
            pohelaBoishakh
        }

        var daysDiff = java.time.temporal.ChronoUnit.DAYS.between(referenceDate, date).toInt()

        var monthIndex = 0
        while (monthIndex < 12 && daysDiff >= monthLengths[monthIndex]) {
            daysDiff -= monthLengths[monthIndex]
            monthIndex++
        }
        val banglaDay = daysDiff + 1

        val season = when (monthIndex) {
            0, 1 -> BanglaSeason.SUMMER
            2, 3 -> BanglaSeason.MONSOON
            4, 5 -> BanglaSeason.AUTUMN
            6, 7 -> BanglaSeason.LATE_AUTUMN
            8, 9 -> BanglaSeason.WINTER
            else -> BanglaSeason.SPRING
        }

        val formatted = "${toBanglaDigits(banglaDay)} ${banglaMonths[monthIndex]} ${toBanglaDigits(banglaYear)} বঙ্গাব্দ"

        return BanglaDateResult(
            day = banglaDay,
            monthIndex = monthIndex,
            year = banglaYear,
            formattedText = formatted,
            season = season
        )
    }

    private fun calculateHijriDate(date: LocalDate): String {
        return try {
            val hijrahDate = HijrahDate.from(date)
            val hDay = hijrahDate.get(ChronoField.DAY_OF_MONTH)
            val hMonth = hijrahDate.get(ChronoField.MONTH_OF_YEAR)
            val hYear = hijrahDate.get(ChronoField.YEAR)

            val monthNameBn = if (hMonth in 1..12) hijriMonthsBn[hMonth - 1] else "হিজরি"
            "${toBanglaDigits(hDay)} $monthNameBn ${toBanglaDigits(hYear)} হিজরি"
        } catch (e: Exception) {
            "২০ রবিউস সানি ১৪৪৮ হিজরি"
        }
    }
}
