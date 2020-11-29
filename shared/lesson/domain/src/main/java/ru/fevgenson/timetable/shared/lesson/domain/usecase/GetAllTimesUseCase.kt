package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class GetAllTimesUseCase(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(): List<TimeEntity> = repository.getAllTimes()
}