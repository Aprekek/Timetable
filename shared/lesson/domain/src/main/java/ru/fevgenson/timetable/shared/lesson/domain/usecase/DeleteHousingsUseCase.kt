package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class DeleteHousingsUseCase(
    private val repository: HousingRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteHousing(it)
        }
    }
}