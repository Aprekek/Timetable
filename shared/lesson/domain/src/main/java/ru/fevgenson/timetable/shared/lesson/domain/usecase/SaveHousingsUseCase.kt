package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class SaveHousingsUseCase(
    private val repository: HousingRepository
) {

    suspend operator fun invoke(vararg housings: HousingEntity) {
        housings.forEach {
            repository.saveHousing(it)
        }
    }
}