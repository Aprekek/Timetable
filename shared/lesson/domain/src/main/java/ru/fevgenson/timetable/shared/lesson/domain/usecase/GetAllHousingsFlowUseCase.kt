package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class GetAllHousingsFlowUseCase(
    private val repository: HousingRepository
) {

    operator fun invoke(): Flow<List<HousingEntity>> = repository.getAllHousingsFlow()
}