package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class SaveTeachersUseCase(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke(vararg teachers: TeacherEntity) {
        teachers.forEach {
            repository.saveTeacher(it)
        }
    }
}