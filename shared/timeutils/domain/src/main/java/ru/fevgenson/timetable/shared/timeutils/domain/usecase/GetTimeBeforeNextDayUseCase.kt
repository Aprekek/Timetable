package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import java.util.*

class GetTimeBeforeNextDayUseCase {

    operator fun invoke(): Long {
        val currentTime = Date().time
        val nextDayTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            add(Calendar.DATE, 1)
        }.time.time
        return nextDayTime - currentTime
    }
}