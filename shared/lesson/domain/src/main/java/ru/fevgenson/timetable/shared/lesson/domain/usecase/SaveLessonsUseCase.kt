package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class SaveLessonsUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke(vararg lessons: LessonEntity) {
        lessons.forEach {
            repository.saveLesson(it)
        }
    }
}