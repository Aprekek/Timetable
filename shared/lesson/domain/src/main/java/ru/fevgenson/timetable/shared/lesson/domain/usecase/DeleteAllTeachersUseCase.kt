package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class DeleteAllTeachersUseCase(
    private val repository: TeacherRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllTeachers()
    }
}