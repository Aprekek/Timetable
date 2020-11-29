package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class GetTimeUseCase(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(id: Long): TimeEntity = repository.getTime(id)
}