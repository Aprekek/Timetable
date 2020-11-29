package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class GetAllClassroomsFlowUseCase(
    private val repository: ClassroomRepository
) {

    operator fun invoke(): Flow<List<ClassroomEntity>> = repository.getAllClassroomsFlow()
}