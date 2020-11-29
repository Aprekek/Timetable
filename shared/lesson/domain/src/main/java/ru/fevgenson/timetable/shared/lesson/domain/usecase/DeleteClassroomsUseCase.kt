package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class DeleteClassroomsUseCase(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteClassroom(it)
        }
    }
}