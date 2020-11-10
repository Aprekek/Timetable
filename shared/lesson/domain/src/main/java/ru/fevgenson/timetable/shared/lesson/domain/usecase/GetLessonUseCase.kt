package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(id: Long): Lesson = repository.getLesson(id)
}