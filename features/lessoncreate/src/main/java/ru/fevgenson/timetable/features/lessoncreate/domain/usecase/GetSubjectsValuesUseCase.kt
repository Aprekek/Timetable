package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class GetSubjectsValuesUseCase(private val repository: SubjectRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllSubjects().map { it.subject }
}