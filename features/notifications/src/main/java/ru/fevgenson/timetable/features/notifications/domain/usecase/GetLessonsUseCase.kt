package ru.fevgenson.timetable.features.notifications.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.features.notifications.domain.entities.NotificationLesson
import ru.fevgenson.timetable.features.notifications.domain.utils.toNotificationLesson
import ru.fevgenson.timetable.libraries.database.domain.repository.LessonRepository

internal class GetLessonsUseCase(private val repository: LessonRepository) {

    operator fun invoke(
        weekType: Int,
        day: Int
    ): LiveData<List<NotificationLesson>> = liveData {
        withContext(Dispatchers.IO) {
            repository.getLessons(weekType, day).map { lessons ->
                lessons.map { lesson -> lesson.toNotificationLesson() }
            }.collect {
                emit(it)
            }
        }
    }
}