package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class DeleteAllLessonsUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllLessons()
    }
}