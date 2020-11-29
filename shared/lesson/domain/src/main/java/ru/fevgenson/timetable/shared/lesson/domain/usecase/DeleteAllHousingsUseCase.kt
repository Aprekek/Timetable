package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class DeleteAllHousingsUseCase(
    private val repository: HousingRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllHousings()
    }
}