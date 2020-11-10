package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepository

class GetTeachersUseCase(private val repository: FieldsRepository) {

    suspend operator fun invoke(): List<DomainTeacherEntity> = repository.getAllTeachers()
}