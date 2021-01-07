package ru.fevgenson.timetable.shared.timeutils.domain.converter

import java.util.*

internal class CalendarConverterImpl : CalendarConverter {

    private companion object {

        const val CALENDAR_DAY_OFFSET = 2
        const val SUNDAY = 6
    }

    override fun convertWeekDay(calendarWeekDay: Int): Int =
        if (calendarWeekDay == Calendar.SUNDAY) {
            SUNDAY
        } else {
            calendarWeekDay - CALENDAR_DAY_OFFSET
        }
}