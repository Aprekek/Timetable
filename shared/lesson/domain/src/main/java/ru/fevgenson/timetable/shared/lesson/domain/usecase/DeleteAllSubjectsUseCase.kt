package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class DeleteAllSubjectsUseCase(
    private val repository: SubjectRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllSubjects()
    }
}