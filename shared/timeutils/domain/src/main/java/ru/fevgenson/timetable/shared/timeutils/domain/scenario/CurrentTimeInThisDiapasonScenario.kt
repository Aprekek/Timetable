package ru.fevgenson.timetable.shared.timeutils.domain.scenario

import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase

class CurrentTimeInThisDiapasonScenario(
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val timeFormatter: TimeFormatter
) {

    operator fun invoke(diapason: String): Boolean {
        val currentTime = getCurrentTimeUseCase()
        val start = timeFormatter.getMinutes(diapason, TimeFormatter.TimeBorders.START)
        val end = timeFormatter.getMinutes(diapason, TimeFormatter.TimeBorders.END)
        return currentTime in start..end
    }
}