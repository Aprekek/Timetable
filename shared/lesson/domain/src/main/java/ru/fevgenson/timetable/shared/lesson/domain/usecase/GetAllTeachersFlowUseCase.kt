package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class GetAllTeachersFlowUseCase(
    private val repository: TeacherRepository
) {

    operator fun invoke(): Flow<List<TeacherEntity>> = repository.getAllTeachersFlow()
}