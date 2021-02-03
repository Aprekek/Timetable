package ru.fevgenson.timetable.shared.notifications.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository
import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity
import ru.fevgenson.timetable.shared.notifications.domain.mapper.toNotificationLessonEntity

class GetNotificationLessonUseCase(
    private val repository: LessonRepository,
) {

    suspend operator fun invoke(id: Long): NotificationLessonEntity =
        repository.getLesson(id).toNotificationLessonEntity()
}