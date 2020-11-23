package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class SaveClassroomsUseCase(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke(vararg classrooms: ClassroomEntity) {
        classrooms.forEach {
            repository.saveClassroom(it)
        }
    }
}