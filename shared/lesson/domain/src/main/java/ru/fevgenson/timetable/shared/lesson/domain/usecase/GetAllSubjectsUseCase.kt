package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class GetAllSubjectsUseCase(
    private val repository: SubjectRepository
) {

    suspend operator fun invoke(): List<SubjectEntity> = repository.getAllSubjects()
}