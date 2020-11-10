package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.libraries.database.domain.repository.FieldsRepository
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity

class GetTeachersUseCase(private val repository: FieldsRepository) {

    suspend operator fun invoke(): List<DomainTeacherEntity> = repository.getAllTeachers()
}