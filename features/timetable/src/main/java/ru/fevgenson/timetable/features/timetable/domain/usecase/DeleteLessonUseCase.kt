package ru.fevgenson.timetable.features.timetable.domain.usecase

import ru.fevgenson.timetable.libraries.database.domain.repository.LessonRepository

class DeleteLessonUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(id: Long) {
        repository.deleteLesson(id)
    }
}