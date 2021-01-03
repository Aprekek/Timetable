package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class GetTypesValuesUseCase(private val repository: TypeRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllTypes().map { it.type }
}