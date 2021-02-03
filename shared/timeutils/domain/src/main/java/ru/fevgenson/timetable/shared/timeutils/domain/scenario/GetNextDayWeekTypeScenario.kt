package ru.fevgenson.timetable.shared.timeutils.domain.scenario

import ru.fevgenson.timetable.shared.timeutils.domain.constants.WeekTypes
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase

class GetNextDayWeekTypeScenario(
    private val getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase,
    private val getCurrentDayUseCase: GetCurrentDayUseCase
) {

    private companion object {

        const val SUNDAY = 6
    }

    operator fun invoke(): Int = when {
        getCurrentDayUseCase() == SUNDAY -> getCurrentWeekTypeUseCase().revertWeekType()
        else -> getCurrentWeekTypeUseCase()
    }

    private fun Int.revertWeekType(): Int = when (this) {
        WeekTypes.FIRST_WEEK -> WeekTypes.SECOND_WEEK
        WeekTypes.SECOND_WEEK -> WeekTypes.FIRST_WEEK
        else -> throw IllegalArgumentException("Unsupported week type: $this")
    }
}