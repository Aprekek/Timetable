package ru.fevgenson.timetable.shared.timeutils.domain.formatter

interface TimeFormatter {

    enum class TimeBorders {
        START, END
    }

    fun format(minutes: Int): String
    fun format(hours: Int, minutes: Int): String
    fun format(startTime: String, endTime: String): String
    fun getMinutes(diapason: String, border: TimeBorders): Int
    fun getMinutes(hours: Int, minutes: Int): Int
    fun getHours(minutes: Int): Int
    fun getMinutesWithoutHours(minutes: Int): Int
}