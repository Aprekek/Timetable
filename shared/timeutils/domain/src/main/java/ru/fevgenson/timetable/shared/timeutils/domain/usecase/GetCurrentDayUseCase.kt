package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverter
import java.util.*

class GetCurrentDayUseCase(
    private val calendarConverter: CalendarConverter
) {

    operator fun invoke(): Int {
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return calendarConverter.convertWeekDay(currentDay)
    }
}