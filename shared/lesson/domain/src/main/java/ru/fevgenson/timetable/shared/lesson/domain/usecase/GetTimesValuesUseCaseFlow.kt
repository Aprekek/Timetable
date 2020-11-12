package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepositoryFlow

class GetTimesValuesUseCaseFlow(private val repository: FieldsRepositoryFlow) {

    operator fun invoke(): Flow<List<String>> =
        repository.getAllTimes().flowOn(Dispatchers.IO)
}