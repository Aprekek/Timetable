package ru.fevgenson.timetable.shared.notifications.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository
import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity
import ru.fevgenson.timetable.shared.notifications.domain.mapper.toNotificationLessonEntities

class GetNotificationLessonsFlowUseCase(
    private val repository: LessonRepository,
) {

    operator fun invoke(
        weekType: Int,
        day: Int
    ): Flow<List<NotificationLessonEntity>> =
        repository.getLessonsFlow(weekType, day).map {
            it.toNotificationLessonEntities()
        }
}