package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class DeleteAllTypesUseCase(
    private val repository: TypeRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllTypes()
    }
}