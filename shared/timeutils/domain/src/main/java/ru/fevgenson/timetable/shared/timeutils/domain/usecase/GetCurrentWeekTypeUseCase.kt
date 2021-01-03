package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import ru.fevgenson.timetable.shared.timeutils.domain.constants.WeekTypes
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverter
import java.util.*
import java.util.concurrent.TimeUnit

class GetCurrentWeekTypeUseCase(
    private val calendarConverter: CalendarConverter
) {

    private companion object {

        const val DAYS_IN_WEEK = 7
    }

    operator fun invoke(): Int {
        val calendar = Calendar.getInstance()
        calendar.setFirstEducatingDay()
        val difference = calendar.getDiffBetweenTodayInMillisecond()
        val daysOffset = calendarConverter.convertWeekDay(calendar.get(Calendar.DAY_OF_WEEK))
        val differenceInDays = TimeUnit.MILLISECONDS.toDays(difference) + daysOffset
        return ((differenceInDays / DAYS_IN_WEEK) % WeekTypes.WEEK_TYPES_COUNT).toInt()
    }

    private fun Calendar.setFirstEducatingDay() {
        //Был ли первый учебный день в прошлом году
        if (get(Calendar.MONTH) < Calendar.SEPTEMBER) {
            add(Calendar.YEAR, -1)
        }
        //Устанавливаем первое сентября
        set(Calendar.MONTH, Calendar.SEPTEMBER)
        set(Calendar.DAY_OF_MONTH, 1)
        //Если первое сенября было воскресенье, то устанавливаем второе
        if (get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            set(Calendar.DAY_OF_MONTH, 2)
        }
    }

    private fun Calendar.getDiffBetweenTodayInMillisecond(): Long {
        val calendarDay = time
        val today = Date()
        return today.time - calendarDay.time
    }
}