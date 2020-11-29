package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class GetAllSubjectsFlowUseCase(
    private val repository: SubjectRepository
) {

    operator fun invoke(): Flow<List<SubjectEntity>> = repository.getAllSubjectsFlow()
}