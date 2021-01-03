package ru.fevgenson.timetable.shared.timeutils.domain.scenario

import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase
import java.text.SimpleDateFormat
import java.util.*

class GetWeekDatesScenario(
    private val getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase
) {

    operator fun invoke(weekType: Int): List<String> {
        val currentWeekType = getCurrentWeekTypeUseCase()
        val calendar = Calendar.getInstance()
        if (weekType != currentWeekType) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }
        val formatter = SimpleDateFormat("dd.MM.yy", Locale("rus"))
        val result = mutableListOf<String>()
        for (day in Calendar.SUNDAY..Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, day)
            result.add(formatter.format(calendar.time))
        }
        return result.apply {
            add(first())
            removeFirst()
        }
    }
}