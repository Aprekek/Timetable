package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonsFlowByWeekTypeAndDayUseCase(
    private val repository: LessonRepository
) {

    operator fun invoke(
        weekType: Int,
        day: Int
    ): Flow<List<LessonEntity>> = repository.getLessonsFlow(weekType, day)
}