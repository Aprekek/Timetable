package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetAllLessonsUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke(): List<LessonEntity> = repository.getAllLessons()
}