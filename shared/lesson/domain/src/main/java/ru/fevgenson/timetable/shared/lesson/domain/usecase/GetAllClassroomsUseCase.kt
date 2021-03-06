package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class GetAllClassroomsUseCase(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke(): List<ClassroomEntity> = repository.getAllClassrooms()
}