package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class GetAllTypesUseCase(
    private val repository: TypeRepository
) {

    suspend operator fun invoke(): List<TypeEntity> = repository.getAllTypes()
}