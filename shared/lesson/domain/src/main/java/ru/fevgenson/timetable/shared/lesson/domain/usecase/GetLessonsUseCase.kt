package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.shared.lesson.domain.entities.TimetableLesson
import ru.fevgenson.timetable.shared.lesson.domain.entities.toTimetableLessons
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonsUseCase(private val repository: LessonRepository) {

    operator fun invoke(
        weekType: Int,
        day: Int
    ): Flow<List<TimetableLesson>> =
        repository.getLessonsByDay(weekType, day).map { lessons ->
            lessons.toTimetableLessons()
        }
}