package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepositoryFlow

class GetSubjectsValuesUseCaseFlow(private val repository: FieldsRepositoryFlow) {

    operator fun invoke(): Flow<List<String>> = repository.getAllSubjects()
}