package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class SaveSubjectsUseCase(
    private val repository: SubjectRepository
) {

    suspend operator fun invoke(vararg subjects: SubjectEntity) {
        subjects.forEach {
            repository.saveSubject(it)
        }
    }
}