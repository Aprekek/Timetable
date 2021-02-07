package ru.fevgenson.timetable.shared.timeutils.domain.scenario

import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase

class GetMinutesBeforeStartScenario(
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val timeFormatter: TimeFormatter,
) {

    operator fun invoke(
        diapason: String,
    ): Int? {
        val currentTime = getCurrentTimeUseCase()
        val startTime = timeFormatter.getMinutes(diapason, TimeFormatter.TimeBorders.START)
        return (startTime - currentTime).takeIf { it > 0 }
    }
}