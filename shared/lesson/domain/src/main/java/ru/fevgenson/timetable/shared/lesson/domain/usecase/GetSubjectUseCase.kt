package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class GetSubjectUseCase(
    private val repository: SubjectRepository
) {

    suspend operator fun invoke(id: Long): SubjectEntity = repository.getSubject(id)
}