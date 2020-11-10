package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class DeleteLessonUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(id: Long) {
        repository.deleteLesson(id)
    }
}