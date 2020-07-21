package ru.fevgenson.timetable.libraries.core.dateutils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val FIRST_WEEK = 0
    const val SECOND_WEEK = 1

    private const val DAY_MILLISECOND = 86400000
    private const val WEEK_DAYS = 7
    private const val WEEK_TYPES = 2
    private const val CALENDAR_DAY_DIFF = 2
    private const val SUNDAY = 6

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

    fun getCurrentWeek(): Int {
        val calendar = Calendar.getInstance()
        //Ставим первый учебный день
        if (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER) {
            calendar.add(Calendar.YEAR, -1)
        }
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.set(Calendar.DAY_OF_MONTH, 2)
        }
        //Считаем недели
        val firstDay = calendar.time
        val today = Date()
        val diffDays = (today.time - firstDay.time).toDouble() / DAY_MILLISECOND
        // -2 так как воскресенье не учитывается, а понедельник = 2
        val diffWeeks =
            (diffDays + calendar.get(Calendar.DAY_OF_WEEK) - CALENDAR_DAY_DIFF) / WEEK_DAYS
        return if (diffWeeks.toInt() % WEEK_TYPES == FIRST_WEEK) FIRST_WEEK else SECOND_WEEK
    }

    fun getCurrentDay(): Int {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return if (currentDay == Calendar.SUNDAY) SUNDAY else currentDay - CALENDAR_DAY_DIFF
    }
}