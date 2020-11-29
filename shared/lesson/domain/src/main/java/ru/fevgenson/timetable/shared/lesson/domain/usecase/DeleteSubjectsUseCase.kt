package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class DeleteSubjectsUseCase(
    private val repository: SubjectRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteSubject(it)
        }
    }
}