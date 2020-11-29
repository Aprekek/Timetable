package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class GetAllTimesFlowUseCase(
    private val repository: TimeRepository
) {

    operator fun invoke(): Flow<List<TimeEntity>> = repository.getAllTimesFlow()
}