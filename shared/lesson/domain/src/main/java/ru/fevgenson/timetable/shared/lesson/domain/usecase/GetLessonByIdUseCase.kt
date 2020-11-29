package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonByIdUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke(id: Long): LessonEntity = repository.getLesson(id)
}