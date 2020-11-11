package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepositoryFlow

class GetTeachersUseCaseFlow(private val repository: FieldsRepositoryFlow) {

    operator fun invoke(): Flow<List<DomainTeacherEntity>> = repository.getAllTeachers()
}