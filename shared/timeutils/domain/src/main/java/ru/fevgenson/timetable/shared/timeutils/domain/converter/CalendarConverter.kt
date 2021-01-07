package ru.fevgenson.timetable.shared.timeutils.domain.converter

interface CalendarConverter {

    fun convertWeekDay(calendarWeekDay: Int): Int
}