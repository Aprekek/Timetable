package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetAllLessonsFlowUseCase(
    private val repository: LessonRepository
) {

    operator fun invoke(): Flow<List<LessonEntity>> = repository.getAllLessonsFlow()
}