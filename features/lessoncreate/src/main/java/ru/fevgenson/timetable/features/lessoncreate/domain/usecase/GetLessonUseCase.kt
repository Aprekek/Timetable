package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson
import ru.fevgenson.timetable.libraries.database.domain.repository.LessonRepository

class GetLessonUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(id: Long): Lesson = repository.getLesson(id)
}