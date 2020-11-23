package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class DeleteTypesUseCase(
    private val repository: TypeRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteType(it)
        }
    }
}