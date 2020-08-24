package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.libraries.database.data.tables.TeacherEntity
import ru.fevgenson.timetable.libraries.database.domain.repository.FieldsRepository

class GetTeachersUseCase(private val repository: FieldsRepository) {

    suspend operator fun invoke(): List<TeacherEntity> = repository.getAllTeachers()
}