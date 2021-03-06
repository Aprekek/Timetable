package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class DeleteLessonsUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteLesson(it)
        }
    }
}