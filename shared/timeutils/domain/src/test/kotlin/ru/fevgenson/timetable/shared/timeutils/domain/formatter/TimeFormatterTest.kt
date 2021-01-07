package ru.fevgenson.timetable.shared.timeutils.domain.formatter

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormatterTest {

    companion object {

        const val STRING_21_30 = "21:30"
        const val FULL_MINUTES_21_30 = 1290
        const val HOURS_21_30 = 21
        const val MINUTES_21_30 = 30

        const val STRING_09_05 = "09:05"
        const val FULL_MINUTES_09_05 = 545
        const val HOURS_09_05 = 9
        const val MINUTES_09_05 = 5

        const val DIAPASON = "$STRING_09_05-$STRING_21_30"
    }

    private val formatter = TimeFormatterImpl()

    @Test
    fun `format full_minutes_21_30 EXPECT string_21_30`() {
        val formattedTime = formatter.format(FULL_MINUTES_21_30)
        assertEquals(formattedTime, STRING_21_30)
    }

    @Test
    fun `format full_minutes_09_05 EXPECT string_09_05`() {
        val formattedTime = formatter.format(FULL_MINUTES_09_05)
        assertEquals(formattedTime, STRING_09_05)
    }

    @Test
    fun `format hours_21_30 and minutes_21_30 EXPECT string_21_30`() {
        val formattedTime = formatter.format(HOURS_21_30, MINUTES_21_30)
        assertEquals(formattedTime, STRING_21_30)
    }

    @Test
    fun `format hours_09_05 and minutes_09_05 EXPECT string_09_05`() {
        val formattedTime = formatter.format(HOURS_09_05, MINUTES_09_05)
        assertEquals(formattedTime, STRING_09_05)
    }

    @Test
    fun `format string_09_05 and string_21_30 EXPECT diapason`() {
        val formattedTime = formatter.format(STRING_09_05, STRING_21_30)
        assertEquals(formattedTime, DIAPASON)
    }

    @Test
    fun `format diapason and start EXPECT full_minutes_09_05`() {
        val formattedTime = formatter.getMinutes(DIAPASON, TimeFormatter.TimeBorders.START)
        assertEquals(formattedTime, FULL_MINUTES_09_05)
    }

    @Test
    fun `format diapason and end EXPECT full_minutes_21_30`() {
        val formattedTime = formatter.getMinutes(DIAPASON, TimeFormatter.TimeBorders.END)
        assertEquals(formattedTime, FULL_MINUTES_21_30)
    }
}