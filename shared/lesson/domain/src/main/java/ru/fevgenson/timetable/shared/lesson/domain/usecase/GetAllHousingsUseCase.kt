package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class GetAllHousingsUseCase(
    private val repository: HousingRepository
) {

    suspend operator fun invoke(): List<HousingEntity> = repository.getAllHousings()
}