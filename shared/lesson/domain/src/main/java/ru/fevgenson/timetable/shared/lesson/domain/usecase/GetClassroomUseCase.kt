package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class GetClassroomUseCase(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke(id: Long): ClassroomEntity = repository.getClassroom(id)
}