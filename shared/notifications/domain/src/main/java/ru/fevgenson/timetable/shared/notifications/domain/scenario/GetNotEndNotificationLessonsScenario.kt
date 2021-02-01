package ru.fevgenson.timetable.shared.notifications.domain.scenario

import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase

class GetNotEndNotificationLessonsScenario(
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val timeFormatter: TimeFormatter
) {

    operator fun invoke(
        lessons: List<NotificationLessonEntity>
    ): List<NotificationLessonEntity> {
        val currentTime = getCurrentTimeUseCase()
        return lessons.filter {
            val endTime = timeFormatter.getMinutes(it.time, TimeFormatter.TimeBorders.END)
            endTime <= currentTime
        }
    }
}