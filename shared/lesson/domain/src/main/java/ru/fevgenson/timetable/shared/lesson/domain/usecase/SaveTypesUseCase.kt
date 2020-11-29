package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class SaveTypesUseCase(
    private val repository: TypeRepository
) {

    suspend operator fun invoke(vararg types: TypeEntity) {
        types.forEach {
            repository.saveType(it)
        }
    }
}