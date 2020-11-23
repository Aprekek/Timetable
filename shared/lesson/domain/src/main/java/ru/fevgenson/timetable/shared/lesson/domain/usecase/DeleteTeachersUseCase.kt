package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class DeleteTeachersUseCase(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteTeacher(it)
        }
    }
}