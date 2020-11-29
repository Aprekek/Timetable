package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class GetAllTeachersUseCase(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke(): List<TeacherEntity> = repository.getAllTeachers()
}