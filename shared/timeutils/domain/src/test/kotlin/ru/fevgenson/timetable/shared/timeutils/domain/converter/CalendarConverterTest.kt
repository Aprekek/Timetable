package ru.fevgenson.timetable.shared.timeutils.domain.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class CalendarConverterTest {

    private companion object {

        const val MONDAY = 0
        const val TUESDAY = 1
        const val WEDNESDAY = 2
        const val THURSDAY = 3
        const val FRIDAY = 4
        const val SATURDAY = 5
        const val SUNDAY = 6
    }

    private val converter = CalendarConverterImpl()

    @Test
    fun `convert calendar monday EXPECT monday`() {
        val convertedDay = converter.convertWeekDay(Calendar.MONDAY)
        assertEquals(convertedDay, MONDAY)
    }

    @Test
    fun `convert calendar tuesday EXPECT tuesday`() {
        val convertedDay = converter.convertWeekDay(Calendar.TUESDAY)
        assertEquals(convertedDay, TUESDAY)
    }

    @Test
    fun `convert calendar wednesday EXPECT wednesday`() {
        val convertedDay = converter.convertWeekDay(Calendar.WEDNESDAY)
        assertEquals(convertedDay, WEDNESDAY)
    }

    @Test
    fun `convert calendar thursday EXPECT thursday`() {
        val convertedDay = converter.convertWeekDay(Calendar.THURSDAY)
        assertEquals(convertedDay, THURSDAY)
    }

    @Test
    fun `convert calendar friday EXPECT friday`() {
        val convertedDay = converter.convertWeekDay(Calendar.FRIDAY)
        assertEquals(convertedDay, FRIDAY)
    }

    @Test
    fun `convert calendar saturday EXPECT saturday`() {
        val convertedDay = converter.convertWeekDay(Calendar.SATURDAY)
        assertEquals(convertedDay, SATURDAY)
    }

    @Test
    fun `convert calendar sunday EXPECT sunday`() {
        val convertedDay = converter.convertWeekDay(Calendar.SUNDAY)
        assertEquals(convertedDay, SUNDAY)
    }
}