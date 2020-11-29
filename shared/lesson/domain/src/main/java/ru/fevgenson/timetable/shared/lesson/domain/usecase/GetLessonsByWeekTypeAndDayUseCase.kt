package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonsByWeekTypeAndDayUseCase(
    private val repository: LessonRepository
) {

    suspend operator fun invoke(
        weekType: Int,
        day: Int
    ): List<LessonEntity> = repository.getLessons(weekType, day)
}