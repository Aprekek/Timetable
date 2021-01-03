package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class GetClassroomsValuesUseCase(private val repository: ClassroomRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllClassrooms().map { it.classroom }
}