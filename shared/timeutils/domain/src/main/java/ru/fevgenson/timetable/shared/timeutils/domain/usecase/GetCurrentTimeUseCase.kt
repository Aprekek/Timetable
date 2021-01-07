package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import java.util.*
import java.util.concurrent.TimeUnit

class GetCurrentTimeUseCase {

    operator fun invoke(): Int {
        val calendar = Calendar.getInstance().apply { time = Date() }
        val hours = calendar.get(Calendar.HOUR_OF_DAY).toLong()
        val minutes = calendar.get(Calendar.MINUTE).toLong()
        return (TimeUnit.HOURS.toMinutes(hours) + minutes).toInt()
    }
}