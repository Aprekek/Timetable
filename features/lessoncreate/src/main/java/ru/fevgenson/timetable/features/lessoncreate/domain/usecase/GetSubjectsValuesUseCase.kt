package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.libraries.database.domain.repository.FieldsRepository

class GetSubjectsValuesUseCase(private val repository: FieldsRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllSubjects().map { it.subject }
}