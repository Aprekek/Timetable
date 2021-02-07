package ru.fevgenson.timetable.shared.timeutils.domain.scenario

import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase

class GetNextDayScenario(
    private val getCurrentDayUseCase: GetCurrentDayUseCase
) {

    private companion object {

        const val SUNDAY = 6
        const val MONDAY = 0
    }

    operator fun invoke(): Int =
        when (val currentDay = getCurrentDayUseCase()) {
            SUNDAY -> MONDAY
            else -> currentDay + 1
        }
}