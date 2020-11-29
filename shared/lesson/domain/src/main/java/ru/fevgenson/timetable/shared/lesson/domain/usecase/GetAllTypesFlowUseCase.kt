package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class GetAllTypesFlowUseCase(
    private val repository: TypeRepository
) {

    operator fun invoke(): Flow<List<TypeEntity>> = repository.getAllTypesFlow()
}