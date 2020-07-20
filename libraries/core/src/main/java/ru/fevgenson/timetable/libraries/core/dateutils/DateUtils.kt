package ru.fevgenson.timetable.libraries.core.dateutils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val FIRST_WEEK = 0
    const val SECOND_WEEK = 1

    fun getWeekDates(weekTypes: Int): List<String> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_YEAR, weekTypes)
        val formatter = SimpleDateFormat("dd.MM.yy", Locale("rus"))
        val result = mutableListOf<String>()
        for (day in Calendar.SUNDAY..Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, day)
            result.add(formatter.format(calendar.time))
        }
        return result.apply {
            add(first())
            removeAt(0)
        }
    }
}